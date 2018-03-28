package com.zainab.evaluation.presentation.latestmovies

import com.zainab.evaluation.domain.model.LatestMovies
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LatestMoviesModel @Inject constructor():LatestMoviesContract.Model {
    override val releaseDateUpperBandFilterString: String?
        get() = formatDate(releaseDateUpperBandFilter)

    override val releaseDateLowerBandFilterString: String?
        get() = formatDate(releaseDateLowerBandFilter)

    override val releaseDateUpperBandFilterLabel: String?
        get() = formatDateLabel(releaseDateUpperBandFilter)

    override val releaseDateLowerBandFilterLabel: String?
        get() = formatDateLabel(releaseDateLowerBandFilter)


    private fun formatDate(calendar: Calendar?): String? {
        return if(calendar == null) null
        else SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
    }

    private fun formatDateLabel(calendar: Calendar?): String? {
        return if(calendar == null) ""
        else SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
    }

    override var releaseDateLowerBandFilter: Calendar? = null

    override var releaseDateUpperBandFilter: Calendar? = null

    override var latestMovies: LatestMovies? = null

    override fun totalPage() = latestMovies?.totalPages ?: 0
}