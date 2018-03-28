package com.zainab.evaluation.presentation.navigator

import com.zainab.evaluation.domain.model.MovieDisplayItem

interface NavigatorContract {
    fun goToMovieDetail(movie: MovieDisplayItem)
}