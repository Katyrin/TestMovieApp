package com.katyrin.testmovieapp.model.api

import com.katyrin.testmovieapp.model.data.FilmsDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("sequeniatesttask/films.json")
    fun getFilms(): Call<FilmsDTO>
}