package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.api.ApiService
import com.katyrin.testmovieapp.model.data.FilmsDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getFilms(): FilmsDTO = apiService.getFilms()
}