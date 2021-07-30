package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.data.FilmDTO

interface LocalDataSource {
    suspend fun getFilms(): List<FilmDTO>
    suspend fun putFilms(films: List<FilmDTO>)
    suspend fun getFilmsByGenre(genre: String?): List<FilmDTO>
}