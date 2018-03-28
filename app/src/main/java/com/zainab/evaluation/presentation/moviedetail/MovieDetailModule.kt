package com.zainab.evaluation.presentation.moviedetail

import com.zainab.evaluation.dependencyinjection.activity.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailModule {
    @Binds
    @ActivityScope
    abstract fun movieDetailPresenter(movieDetailPresenter: MovieDetailPresenter): MovieDetailContract.Presenter
}