package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmsDTO
import com.katyrin.testmovieapp.model.datasource.RemoteDataSource
import retrofit2.Callback
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FilmsRepository {
    override fun getFilms(callback: Callback<FilmsDTO>) {
        remoteDataSource.getFilms(callback)
    }
}