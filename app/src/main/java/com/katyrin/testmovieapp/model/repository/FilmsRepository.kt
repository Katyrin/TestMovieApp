package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmsDTO

interface FilmsRepository {
    suspend fun getFilms(): FilmsDTO
}