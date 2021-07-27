package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.api.ApiService
import com.katyrin.testmovieapp.model.data.FilmsDTO
import retrofit2.Callback
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {

    override fun getFilms(callback: Callback<FilmsDTO>) {
        apiService.getFilms().enqueue(callback)
    }
}