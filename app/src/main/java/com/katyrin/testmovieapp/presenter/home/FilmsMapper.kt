package com.katyrin.testmovieapp.presenter.home

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import javax.inject.Inject

class FilmsMapper @Inject constructor() {

    fun getBaseRecyclerData(films: List<FilmDTO>): List<RecyclerData> =
        mutableListOf<RecyclerData>().apply {
            add(RecyclerData.Header(HEADER_GENRES))
            addAll(getGenres(films))
            add(RecyclerData.Header(HEADER_FILMS))
        }

    private fun getGenres(films: List<FilmDTO>): List<RecyclerData.Genre> {
        val genres: MutableSet<RecyclerData.Genre> = mutableSetOf()
        for (film in films) {
            if (film.genres != null)
                for (genre in film.genres) {
                    genres.add(RecyclerData.Genre(genre))
                }
        }
        return genres.toList().sortedBy { it.genre }
    }

    fun getSortRecyclerDataFilms(films: List<FilmDTO>): List<RecyclerData.Film> {
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