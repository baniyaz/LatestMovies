package com.zainab.evaluation.presentation.latestmovies

import android.app.DatePickerDialog
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.base.BaseContract
import java.util.*

interface LatestMoviesContract {
    interface View : BaseContract.View {
        fun renderLatestMovies(latestMovies: LatestMovies)
        fun showMessage(message: String?)
        fun hideLoader()
        fun showLoader()
        fun showDatePicker(onDateSetListener: DatePickerDialog.OnDateSetListener, currentDate: Calendar)
        fun setLowerBandDateText(s: String)
        fun setUpperBandDateText(s: String)
        fun addNextPageToAdapter(latestMovies: LatestMovies)
        fun addLoadingItemToLatestMovies()
    }

    interface Presenter : BaseContract.Presenter<View>, InfiniteScrollListener.InfiniteLoader{
        fun initialize()
        fun onMovieClicked(movie: MovieDisplayItem)
        fun onSuccess(latestMovies: LatestMovies)
        fun onError(message: String?)
        fun lowerBandDateClicked()
        fun upperBandDateClicked()
    }

    interface Model {
        var latestMovies: LatestMovies?
        var releaseDateLowerBandFilter: Calendar?
        var releaseDateUpperBandFilter: Calendar?
        val releaseDateLowerBandFilterString: String?
        val releaseDateUpperBandFilterString: String?
        val releaseDateUpperBandFilterLabel: String?
        val releaseDateLowerBandFilterLabel: String?
        fun totalPage(): Int
    }
}
