package com.katyrin.testmovieapp.model.interactor

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.model.networkstatus.NetworkStateRepository
import com.katyrin.testmovieapp.model.repository.FilmsRepository
import com.katyrin.testmovieapp.presenter.home.FilmsMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainInteractorImpl @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val networkStateRepository: NetworkStateRepository,
    private val filmsMapper: FilmsMapper
) : MainInteractor {

    private var baseRecyclerData: MutableList<RecyclerData> = mutableListOf()

    override suspend fun getFilms(): List<RecyclerData> =
        withContext(Dispatchers.IO) {
            val films: List<FilmDTO> =
                if (networkStateRepository.isOnline()) filmsRepository.getRemoteFilms() ?: listOf()
                else filmsRepository.getLocalFilms()
            filmsRepository.putFilms(films)
            getRecyclerDataList(films)
        }

    private suspend fun getRecyclerDataList(films: List<FilmDTO>): List<RecyclerData> {
        baseRecyclerData = mappingBaseData(films).toMutableList()
        return concatRecyclerData(mappingFilmData(films))
    }

    private fun concatRecyclerData(films: List<RecyclerData>): List<RecyclerData> =
        mutableListOf<RecyclerData>().apply {
            addAll(baseRecyclerData)
            addAll(films)
        }

    private suspend fun mappingFilmData(films: List<FilmDTO>): List<RecyclerData> =
        withContext(Dispatchers.Default) { filmsMapper.getSortRecyclerDataFilms(films) }

    private suspend fun mappingBaseData(films: List<FilmDTO>): List<RecyclerData> =
        withContext(Dispatchers.Default) { filmsMapper.getBaseRecyclerData(films) }

    override suspend fun getFilmsByGenre(genre: String?): List<RecyclerData> =
        withContext(Dispatchers.IO) {
            val films: List<FilmDTO> = filmsRepository.getFilmsByGenre(genre)
            getRecyclerDataListByGenre(films)
        }

    private suspend fun getRecyclerDataListByGenre(films: List<FilmDTO>): List<RecyclerData> =
        concatRecyclerData(mappingFilmData(films))
}