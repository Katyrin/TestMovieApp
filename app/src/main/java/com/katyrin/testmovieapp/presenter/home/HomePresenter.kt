package com.katyrin.testmovieapp.presenter.home

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.model.repository.FilmsRepository
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: Router,
    private val filmsRepository: FilmsRepository,
    private val filmsMapper: FilmsMapper
) : MvpPresenter<HomeView>() {

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

    fun getFilms(genre: String? = null) {
        viewState.showLoadingState()
        cancelJob()
        presenterCoroutineScope.launch {
            val films: List<FilmDTO> = networkRequest()
            val recyclerData: List<RecyclerData> = mappingData(films, genre)
            viewState.showRecyclerView(recyclerData, genre)
            viewState.showNormalState()
        }
    }

    private suspend fun networkRequest(): List<FilmDTO> =
        withContext(Dispatchers.IO) { filmsRepository.getFilms().films }

    private suspend fun mappingData(films: List<FilmDTO>, genre: String?): List<RecyclerData> =
        withContext(Dispatchers.Default) { filmsMapper.mapIntoRecyclerData(films, genre) }

    override fun onDestroy() {
        cancelJob()
        super.onDestroy()
    }

    private fun cancelJob() {
        presenterCoroutineScope.coroutineContext.cancelChildren()
    }
}