package com.zainab.evaluation.data.remote

import com.zainab.evaluation.domain.model.Configuration
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interactions with the Tmdb api
 */
interface TmdbService {
    @GET("discover/movie")
    fun getLatestMovies(@Query("page") page:Int,
                        @Query("release_date.gte") releaseDateLowerBand: String?,
                        @Query("release_date.lte") releaseDateUpperBand: String?,
                        @Query("sort_by") sortBy:String): Observable<LatestMovies>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id:Int) : Observable<MovieDisplayItem>


    @GET("configuration")
    fun getConfiguration() : Observable<Configuration>

    companion object {
        const val API_URL = "http://api.themoviedb.org/3/"
    }
}