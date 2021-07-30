package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.storage.FilmsDataBase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataBase: FilmsDataBase,
    private val entityMapper: EntityMapper
) : LocalDataSource {
    override suspend fun getFilms(): List<FilmDTO> =
        entityMapper.mapFilmEntityToDto(
            dataBase.filmsDao().getFilms()
        )

    override suspend fun putFilms(films: List<FilmDTO>) {
        dataBase.filmsDao().putFilms(
            entityMapper.mapFilmDtoToEntity(films)
        )
    }

    override suspend fun getFilmsByGenre(genre: String?): List<FilmDTO> =
        entityMapper.mapFilmEntityToDto(
            if (genre.isNullOrEmpty()) dataBase.filmsDao().getFilms()
            else dataBase.filmsDao().getFilmsByGenre(genre)
        )
}