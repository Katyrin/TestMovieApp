package com.katyrin.testmovieapp.model.data

sealed class RecyclerData {
    data class Film(val filmDTO: FilmDTO) : RecyclerData()
    data class Header(val header: String) : RecyclerData()
    data class Genre(val genre: String) : RecyclerData()
}
