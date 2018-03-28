package com.zainab.evaluation.domain.model

class MovieLoadingItem : Movie() {
    override val type: Int
        get() = Movie.LOADING
}