package com.katyrin.testmovieapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey
    val id: Int?,
    val localizedName: String?,
    val name: String?,
    val year: Int?,
    val rating: Double?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String>?
)
