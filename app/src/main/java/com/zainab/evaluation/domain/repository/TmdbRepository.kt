package com.zainab.evaluation.domain.repository

import com.zainab.evaluation.domain.model.Configuration
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import io.reactivex.Observable

interface TmdbRepository{
    fun movie(id: Int): Observable<MovieDisplayItem>
    fun getConfiguration(): Observable<Configuration>
    fun latestMovies(page: Int, releaseDateLowerBand: String?, releaseDateUpperBand: String?, sortBy: String): Observable<LatestMovies>
}