package com.katyrin.testmovieapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmsDTO(
    val films: List<FilmDTO>?
) : Parcelable
