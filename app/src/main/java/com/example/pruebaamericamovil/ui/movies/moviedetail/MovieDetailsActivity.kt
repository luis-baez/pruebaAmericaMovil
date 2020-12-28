package com.example.pruebaamericamovil.ui.movies.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebaamericamovil.R
import com.example.pruebaamericamovil.databinding.ActivityMovieDetailsBinding
import com.example.pruebaamericamovil.model.Movie
import com.example.pruebaamericamovil.ui.loadImage

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private var movie : Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.extras?.get("movie") as Movie
    }

    override fun onResume() {
        super.onResume()
        movie?.let {
            val popularity = "${it.voteAverage}/10"
            binding.run {
                movieDetailPoster.loadImage(it.posterPath,this@MovieDetailsActivity)
                movieTitle.text = it.title
                movieDate.text = it.releaseDate
                movieRating.text = popularity
                movieDescription.text = it.overview
            }

        }

    }
}