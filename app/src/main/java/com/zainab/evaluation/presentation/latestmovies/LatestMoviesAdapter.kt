package com.zainab.evaluation.presentation.latestmovies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.zainab.evaluation.R
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.Movie
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.domain.model.MovieLoadingItem
import com.zainab.evaluation.presentation.TmdbImageConfigurationFactory
import kotlinx.android.synthetic.main.movie_item.view.*

class LatestMoviesAdapter : RecyclerView.Adapter<LatestMoviesAdapter.ViewHolder>() {
    private  var latestMovieItems = mutableListOf<Movie>()
    private var onItemClickListener: MovieItemClickListener? = null
    private lateinit var tmdbImageConfigurationFactory: TmdbImageConfigurationFactory

    interface MovieItemClickListener {
        fun onItemClick(movie: MovieDisplayItem?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            Movie.LOADING -> ViewHolder.LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false))
            Movie.DISPLAY ->  ViewHolder.MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
            else -> throw IllegalStateException("You cannot create a view with invalid type $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = latestMovieItems[position]

        when(holder) {
             is ViewHolder.MovieViewHolder -> {
                movie as MovieDisplayItem
                holder.releaseDate.text = movie.releaseDate

                movie.posterPath?.let {
                    val imageUri = tmdbImageConfigurationFactory.buildPosterImageUri(it)
                    holder.image.movie_item_imageView.setImageURI(imageUri, movie.title)
                }

                holder.itemView.setOnClickListener { _ ->
                    this@LatestMoviesAdapter.onItemClickListener?.onItemClick(movie)
                }
            }
        }
    }

    override fun getItemViewType(position: Int) = latestMovieItems[position].type

    override fun getItemCount() = latestMovieItems.size

    override fun getItemId(position: Int) =
            (latestMovieItems[position] as? MovieDisplayItem)?.id?.toLong()
                    ?:  RecyclerView.NO_ID

    fun setLatestMovies(latestMovies: LatestMovies?) {
        latestMovieItems.clear()
        latestMovieItems.plusAssign(latestMovies?.results ?: mutableListOf())
        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: MovieItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setTmdbImageConfigurationFactory(tmdbImageConfigurationFactory: TmdbImageConfigurationFactory){
        this.tmdbImageConfigurationFactory = tmdbImageConfigurationFactory
    }

    fun addNextPage(latestMovies: LatestMovies) {
        latestMovieItems.removeAt(latestMovieItems.lastIndex)
        latestMovieItems.plusAssign(latestMovies.results ?: emptyList())
        notifyDataSetChanged()
    }

    fun addLoadingItem() {
        if(latestMovieItems.last() !is MovieLoadingItem) {
            latestMovieItems.add(MovieLoadingItem())
            notifyDataSetChanged()
        }
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        class MovieViewHolder(view: View) : ViewHolder(view) {
            var image: SimpleDraweeView = view.findViewById(R.id.movie_item_imageView)
            var releaseDate: TextView = view.findViewById(R.id.movie_item_release_date)
        }

        class LoadingViewHolder(view: View) : ViewHolder(view) {}
    }
}
