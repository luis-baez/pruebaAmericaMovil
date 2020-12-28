package com.example.pruebaamericamovil.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaamericamovil.databinding.ActivityMoviesBinding
import com.example.pruebaamericamovil.model.Movie
import com.example.pruebaamericamovil.ui.movies.moviedetail.MovieDetailsActivity

class MoviesActivity : AppCompatActivity(), MoviesContract.MoviesView {

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var presenter: MoviesContract.MoviesPresenter

    private val moviesAdapter = MoviesAdapter {
        goToMovieDetail(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MoviesPresenterImpl(this)

    }

    override fun onResume() {
        super.onResume()
        binding.rvMovies.apply {
            layoutManager =
                LinearLayoutManager(this@MoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
        presenter.getMovies()
    }

    private fun goToMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showError(error: String) {
        Toast.makeText(
            this,
            error,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onGetMoviesSuccess(movies: List<Movie>) {
        setMovies(true, movies)
        setEmptyState(isVisible = false)
    }

    override fun onGetMoviesError(error: String) {
        setMovies(false)
        setEmptyState(true, error)
    }

    private fun setMovies(isVisible: Boolean, movies: List<Movie>? = null) {
        binding.rvMovies.apply {
            visibility = if (isVisible) View.VISIBLE else View.GONE
            movies?.let {
                moviesAdapter.movies = it
            }
        }
    }

    private fun setEmptyState(isVisible: Boolean, message: String = "") {
        binding.tvEmptyState.apply {
            visibility = if (isVisible) View.VISIBLE else View.GONE
            text = message
        }
    }


}