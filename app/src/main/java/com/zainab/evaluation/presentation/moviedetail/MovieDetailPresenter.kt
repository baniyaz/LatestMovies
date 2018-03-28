package com.zainab.evaluation.presentation.moviedetail


import com.zainab.evaluation.domain.model.MovieDisplayItem
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor() : MovieDetailContract.Presenter {
    private var view: MovieDetailContract.View? = null

    override fun attachView(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun initialize(movie: MovieDisplayItem) {
        view?.renderMovieDetail(movie)
    }
}
