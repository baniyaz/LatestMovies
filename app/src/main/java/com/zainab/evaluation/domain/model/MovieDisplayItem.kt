package com.zainab.evaluation.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
open class MovieDisplayItem(var id: Int?,
                            var title: String?,
                            var posterPath: String?,
                            var backdropPath: String?,
                            var overview: String?,
                            var releaseDate: String?) :Parcelable, Movie(){
    override val type: Int
        get() = Movie.DISPLAY
}