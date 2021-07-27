package com.katyrin.testmovieapp.presenter.home

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import javax.inject.Inject

class FilmsMapper @Inject constructor() {

    fun mapIntoRecyclerData(films: List<FilmDTO>): List<RecyclerData> {
        val recyclerDataList: MutableList<RecyclerData> =
            mutableListOf(RecyclerData.Header(HEADER_GENRES))
        recyclerDataList.addAll(getGenres(films))
        recyclerDataList.add(RecyclerData.Header(HEADER_FILMS))
        recyclerDataList.addAll(getSortFilms(films))
        return recyclerDataList
    }

    private fun getGenres(films: List<FilmDTO>): List<RecyclerData.Genre> {
        val genres: MutableSet<RecyclerData.Genre> = mutableSetOf()
        for (film in films) {
            for (genre in film.genres) {
                genres.add(RecyclerData.Genre(genre))
            }
        }
        return genres.toList().sortedBy { it.genre }
    }

    private fun getSortFilms(films: List<FilmDTO>): List<RecyclerData.Film> {
        val sortFilms = films.sortedBy { it.localizedName }
        val recyclerFilms: MutableList<RecyclerData.Film> = mutableListOf()
        for (sortFilm in sortFilms) {
            recyclerFilms.add(RecyclerData.Film(sortFilm))
        }
        return recyclerFilms
    }

    private companion object {
        const val HEADER_GENRES = "Жанры"
        const val HEADER_FILMS = "Фильмы"
    }
}