package com.example.pruebaamericamovil.ui.movies

import com.example.pruebaamericamovil.model.Movie

interface MoviesContract {

    interface MoviesView {
        fun showError(error: String)
        fun onGetMoviesSuccess(movies: List<Movie>)
        fun onGetMoviesError(error: String)
    }

    interface MoviesPresenter {
        fun getMovies()
    }
}