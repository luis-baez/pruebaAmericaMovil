package com.example.pruebaamericamovil.ui.movies

import com.example.pruebaamericamovil.model.Movies
import com.example.pruebaamericamovil.network.GetMovieData
import com.example.pruebaamericamovil.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPresenterImpl(val view: MoviesContract.MoviesView) : MoviesContract.MoviesPresenter {

    private val API_KEY = "14762a6113b941b9023624307bf90758"
    var getMovieData: GetMovieData? =
        RetrofitService.getRetrofitInstance()?.create(GetMovieData::class.java)

    override fun getMovies() {
        getMovieData?.let {
            val call = it.getTopRatedMovies(API_KEY)
            call.enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful) {
                        response.body().run {
                            this?.data.let { movies->
                                if (!movies.isNullOrEmpty()) view.onGetMoviesSuccess(movies)
                                else view.onGetMoviesError("No hay Peliculas")
                            }
                        }
                    } else {
                        view.showError("Error al consultar peliculas")
                    }
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    call.cancel()
                    this@MoviesPresenterImpl.view.showError("Error al consultar peliculas")
                }
            })
        }

    }


}