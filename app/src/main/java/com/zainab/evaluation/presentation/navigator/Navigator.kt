package com.zainab.evaluation.presentation.navigator

import android.os.Bundle
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.base.BaseActivity
import com.zainab.evaluation.presentation.moviedetail.MovieDetailFragment

class Navigator constructor(private val activity: BaseActivity): NavigatorContract {
    override fun goToMovieDetail(movie: MovieDisplayItem) {
        activity.pushFragmentToBackStack(MovieDetailFragment.newInstance(Bundle().apply {
            putParcelable(MovieDetailFragment.ITEM_ARG, movie)
        }))
    }

}