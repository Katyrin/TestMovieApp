package com.katyrin.testmovieapp.model.datasource

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.FilmEntity
import javax.inject.Inject

class EntityMapper @Inject constructor() {

    fun mapFilmDtoToEntity(films: List<FilmDTO>): List<FilmEntity> {
        val filmsEntity: MutableList<FilmEntity> = mutableListOf()
        for (film in films) {
            filmsEntity.add(
                FilmEntity(
                    film.id,
                    film.localizedName,
                    film.name,
                    film.year,
                    film.rating,
                    film.imageUrl,
                    film.description,
                    film.genres
                )
            )
        }
        return filmsEntity
    }

    fun mapFilmEntityToDto(films: List<FilmEntity>): List<FilmDTO> {
        val filmsDTO: MutableList<FilmDTO> = mutableListOf()
        for (film in films) {
            filmsDTO.add(
                FilmDTO(
                    film.id,
                    film.localizedName,
                    film.name,
                    film.year,
                    film.rating,
                    film.imageUrl,
                    film.description,
                    film.genres
                )
            )
        }
        return filmsDTO
    }
}