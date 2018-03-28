package com.zainab.evaluation.presentation.latestmovies

import com.zainab.evaluation.dependencyinjection.activity.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class LatestMoviesModule {
    @Binds
    @ActivityScope
    abstract fun latestMoviesPresenter(latestMoviesPresenter: LatestMoviesPresenter):LatestMoviesContract.Presenter

    @Binds
    @ActivityScope
    abstract fun latestMoviesModel(latestMoviesModel: LatestMoviesModel):LatestMoviesContract.Model

    @Binds
    @ActivityScope
    abstract fun latestMoviesAdapter(latestMoviesAdapter: LatestMoviesAdapter):LatestMoviesAdapter
}