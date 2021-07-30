package com.katyrin.testmovieapp.model.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katyrin.testmovieapp.model.data.FilmEntity

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FilmsDataBase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}