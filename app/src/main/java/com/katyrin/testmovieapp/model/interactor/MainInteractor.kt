package com.katyrin.testmovieapp.model.interactor

import com.katyrin.testmovieapp.model.data.RecyclerData

interface MainInteractor {
    suspend fun getFilms(): Pair<List<RecyclerData>, String?>
    suspend fun getFilmsByGenre(genre: String?): List<RecyclerData>
}