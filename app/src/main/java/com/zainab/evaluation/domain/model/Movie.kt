package com.zainab.evaluation.domain.model

open class Movie {
    open val type: Int = -1
    companion object {
        const val DISPLAY = 0
        const val LOADING = 1
    }
}