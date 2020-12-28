package com.example.pruebaamericamovil.ui.movies

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaamericamovil.R
import com.example.pruebaamericamovil.databinding.MovieItemBinding
import com.example.pruebaamericamovil.model.Movie
import com.example.pruebaamericamovil.ui.loadImage
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

typealias onMovieClick = (Movie) -> Unit

class MoviesAdapter(private val onMovieClick: onMovieClick) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies: List<Movie> by Delegates.observable(emptyList(), { _, _, _ ->
        notifyDataSetChanged()
    })

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: MovieItemBinding = MovieItemBinding.bind(itemView)

        fun bind(movie: Movie, listener: onMovieClick) {

            binding.movieCv.apply {
                layoutParams = ViewGroup.LayoutParams(
                    screenWidth / 2, getMeasuredPosterHeight(screenWidth / 2)
                )
                setOnClickListener { listener(movie) }
            }
            binding.posterMovie.loadImage(movie.posterPath,itemView.context)
        }



        private val screenWidth: Int
            private get() = Resources.getSystem().displayMetrics.widthPixels

        private fun getMeasuredPosterHeight(width: Int): Int {
            return (width * 1.5f).toInt()
        }

        companion object {
            fun fromParent(parent: ViewGroup) =
                MovieViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.movie_item, parent, false
                    )
                )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.fromParent(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onMovieClick)
    }

    override fun getItemCount() = movies.size
}