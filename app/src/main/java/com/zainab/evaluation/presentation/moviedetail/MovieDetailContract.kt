package com.zainab.evaluation.presentation.moviedetail

import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.base.BaseContract

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun renderMovieDetail(movie: MovieDisplayItem)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun initialize(movie: MovieDisplayItem)
    }
}
