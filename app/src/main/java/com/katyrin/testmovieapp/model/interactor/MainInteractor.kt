package com.katyrin.testmovieapp.model.interactor

import com.katyrin.testmovieapp.model.data.RecyclerData

interface MainInteractor {
    suspend fun getFilms(): List<RecyclerData>
    suspend fun getFilmsByGenre(genre: String?): List<RecyclerData>
}