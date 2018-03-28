package com.zainab.evaluation.dependencyinjection.activity

import com.zainab.evaluation.presentation.latestmovies.LatestMoviesFragment
import com.zainab.evaluation.presentation.latestmovies.LatestMoviesModule
import com.zainab.evaluation.presentation.moviedetail.MovieDetailFragment
import com.zainab.evaluation.presentation.moviedetail.MovieDetailModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, LatestMoviesModule::class, MovieDetailModule::class])
interface ActivityComponent {
    fun inject(latestMoviesFragment: LatestMoviesFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
}
