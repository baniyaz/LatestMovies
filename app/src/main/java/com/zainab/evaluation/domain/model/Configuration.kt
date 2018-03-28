package com.zainab.evaluation.domain.model

import android.annotation.SuppressLint

@SuppressLint("ParcelCreator")
class Configuration{
    //We initialise with these values to make sure we have a fallback if we can't retrieve configuration from api
    var baseUrl = "https://image.tmdb.org/t/p/"
    var posterSizes = listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")
    var backdropSizes = listOf("w300", "w780", "w1280", "original")
}
