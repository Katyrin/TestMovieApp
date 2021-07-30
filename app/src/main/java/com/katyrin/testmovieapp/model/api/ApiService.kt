package com.katyrin.testmovieapp.model.api

import com.katyrin.testmovieapp.model.data.FilmsDTO
import retrofit2.http.GET

interface ApiService {

    @GET("sequeniatesttask/films.json")
    suspend fun getFilms(): FilmsDTO
}