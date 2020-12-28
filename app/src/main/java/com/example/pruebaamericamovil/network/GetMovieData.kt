package com.example.pruebaamericamovil.network

import com.example.pruebaamericamovil.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMovieData {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") userkey: String?): Call<Movies>
}