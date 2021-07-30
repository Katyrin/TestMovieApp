package com.katyrin.testmovieapp.model.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.katyrin.testmovieapp.model.data.FilmEntity

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films")
    suspend fun getFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films WHERE genres LIKE '%' || :genre || '%'")
    suspend fun getFilmsByGenre(genre: String): List<FilmEntity>
}