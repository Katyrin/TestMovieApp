package com.katyrin.testmovieapp.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmDTO(
    val id: Int? = 0,
    @SerializedName("localized_name")
    val localizedName: String? = "",
    val name: String? = "",
    val year: Int? = 0,
    val rating: Double? = 0.0,
    @SerializedName("image_url")
    val imageUrl: String? = "",
    val description: String? = "",
    val genres: List<String>? = listOf()
) : Parcelable
