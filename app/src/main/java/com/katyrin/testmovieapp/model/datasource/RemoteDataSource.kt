package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.data.FilmsDTO

interface RemoteDataSource {
    suspend fun getFilms(): FilmsDTO
}