package com.example.pruebaamericamovil.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movies(
    @SerializedName("results") val data: List<Movie>? = emptyList()
)

data class Movie(
    @SerializedName("vote_average") val voteAverage: Double? = 0.0,
    @SerializedName("title") val title: String? = "",
    @SerializedName("poster_path") val posterPath: String? = "",
    @SerializedName("overview") val overview: String? = "",
    @SerializedName("release_date") var releaseDate: String? = ""
) : Serializable

