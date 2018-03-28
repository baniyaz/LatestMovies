package com.zainab.evaluation.data.remote

import com.zainab.evaluation.domain.model.Configuration
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.domain.repository.TmdbRepository
import io.reactivex.Observable
import javax.inject.Inject

class TmdbRepositoryImpl @Inject constructor(val tmdbService: TmdbService): TmdbRepository {
    override fun latestMovies(page: Int, releaseDateLowerBand: String?, releaseDateUpperBand: String?, sortBy: String): Observable<LatestMovies> {
        return tmdbService.getLatestMovies(page, releaseDateLowerBand, releaseDateUpperBand, sortBy)
    }

    override fun movie(id :Int): Observable<MovieDisplayItem> {
        return tmdbService.getMovie(id)
    }

    override  fun getConfiguration() : Observable<Configuration>{
        return tmdbService.getConfiguration()
    }
}