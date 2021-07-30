package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmsDTO
import com.katyrin.testmovieapp.model.datasource.RemoteDataSource
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FilmsRepository {
    override suspend fun getFilms(): FilmsDTO = remoteDataSource.getFilms()
}