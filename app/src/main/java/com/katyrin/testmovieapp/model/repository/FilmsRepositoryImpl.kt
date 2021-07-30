package com.katyrin.testmovieapp.model.repository

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.datasource.LocalDataSource
import com.katyrin.testmovieapp.model.datasource.RemoteDataSource
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : FilmsRepository {

    override suspend fun getRemoteFilms(): List<FilmDTO>? = remoteDataSource.getFilms().films

    override suspend fun getLocalFilms(): List<FilmDTO> = localDataSource.getFilms()

    override suspend fun putFilms(films: List<FilmDTO>) {
        localDataSource.putFilms(films)
    }

    override suspend fun getFilmsByGenre(genre: String?): List<FilmDTO> =
        localDataSource.getFilmsByGenre(genre)
}