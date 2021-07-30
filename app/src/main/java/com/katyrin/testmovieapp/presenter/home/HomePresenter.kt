package com.katyrin.testmovieapp.presenter.home

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.model.networkstatus.NetworkStateRepository
import com.katyrin.testmovieapp.model.repository.FilmsRepository
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: Router,
    private val filmsRepository: FilmsRepository,
    private val filmsMapper: FilmsMapper,
    private val networkStateRepository: NetworkStateRepository
) : MvpPresenter<HomeView>() {

    private var baseRecyclerData: MutableList<RecyclerData>? = null
    private val presenterCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun handleError(error: Throwable) {
        viewState.showEmptyList()
        viewState.showError(error.message)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getFilms()
    }

    fun navigateToScreen(screen: FragmentScreen) {
        router.navigateTo(screen)
    }

    fun getFilmsByGenre(genre: String? = null) {
        cancelJob()
        presenterCoroutineScope.launch {
            val films: List<FilmDTO> = localRequestByGenre(genre)
            val recyclerData: List<RecyclerData> = concatRecyclerData(mappingFilmData(films))
            viewState.showRecyclerView(recyclerData, genre)
            viewState.showNormalState()
        }
    }

    fun getFilms(genre: String? = null) {
        viewState.showLoadingState()
        cancelJob()
        presenterCoroutineScope.launch {
            val films: List<FilmDTO> = getFilmsDto()
            baseRecyclerData = mappingBaseData(films).toMutableList()
            val recyclerData: List<RecyclerData> = concatRecyclerData(mappingFilmData(films))
            viewState.showRecyclerView(recyclerData, genre)
            viewState.showNormalState()
        }
    }

    private fun concatRecyclerData(films: List<RecyclerData>): List<RecyclerData> {
        val recyclerData: MutableList<RecyclerData> = mutableListOf()
        baseRecyclerData?.let { recyclerData.addAll(it) }
        recyclerData.addAll(films)
        return recyclerData
    }

    private suspend fun getFilmsDto(): List<FilmDTO> =
        withContext(Dispatchers.IO) {
            val films: List<FilmDTO> =
                if (networkStateRepository.isOnline()) filmsRepository.getRemoteFilms() ?: listOf()
                else filmsRepository.getLocalFilms()
            filmsRepository.putFilms(films)
            return@withContext films
        }

    private suspend fun mappingBaseData(films: List<FilmDTO>): List<RecyclerData> =
        withContext(Dispatchers.Default) { filmsMapper.getBaseRecyclerData(films) }

    private suspend fun mappingFilmData(films: List<FilmDTO>): List<RecyclerData> =
        withContext(Dispatchers.Default) { filmsMapper.getSortRecyclerDataFilms(films) }

    private suspend fun localRequestByGenre(genre: String?): List<FilmDTO> =
        withContext(Dispatchers.IO) { filmsRepository.getFilmsByGenre(genre) }

    override fun onDestroy() {
        cancelJob()
        baseRecyclerData = null
        super.onDestroy()
    }

    private fun cancelJob() {
        presenterCoroutineScope.coroutineContext.cancelChildren()
    }
}