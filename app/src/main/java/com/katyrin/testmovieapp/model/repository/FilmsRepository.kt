package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmsDTO
import retrofit2.Callback

interface FilmsRepository {
    fun getFilms(callback: Callback<FilmsDTO>)
}