package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.data.FilmsDTO
import retrofit2.Callback

interface RemoteDataSource {
    fun getFilms(callback: Callback<FilmsDTO>)
}