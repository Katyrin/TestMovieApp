package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmDTO

interface FilmsRepository {
    suspend fun getRemoteFilms(): List<FilmDTO>?
    suspend fun getLocalFilms(): List<FilmDTO>
    suspend fun putFilms(films: List<FilmDTO>)
    suspend fun getFilmsByGenre(genre: String?): List<FilmDTO>
}