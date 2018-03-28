package com.zainab.evaluation.presentation.latestmovies


import android.app.DatePickerDialog
import android.util.Log
import com.zainab.evaluation.domain.GetLatestMoviesUseCase
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.navigator.NavigatorContract
import io.reactivex.observers.DisposableObserver
import java.util.*
import javax.inject.Inject

class LatestMoviesPresenter @Inject constructor(val getLatestMoviesUseCase: GetLatestMoviesUseCase,
                                                val navigatorContract: NavigatorContract,
                                                val latestMoviesModel: LatestMoviesContract.Model)
    : LatestMoviesContract.Presenter {
    private var view: LatestMoviesContract.View? = null

    override fun attachView(view: LatestMoviesContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
        getLatestMoviesUseCase.dispose()
    }

    override fun initialize() {
        //return if our model already has the latest movies
        latestMoviesModel.latestMovies?.let {
            onSuccess(it)
            return
        }

        getLatestMovies()
    }

    fun getLatestMovies() {
        view?.setUpperBandDateText("To: ${latestMoviesModel.releaseDateUpperBandFilterLabel}")

        view?.setLowerBandDateText("From: ${latestMoviesModel.releaseDateLowerBandFilterLabel}")

        view?.showLoader()

        getLatestMoviesUseCase.execute(LatestMoviesObserver(), buildParam(FIRST_PAGE))
    }

    private fun buildParam(nextPage: Int) = GetLatestMoviesUseCase.LatestMovieParam().apply {
        page = nextPage
        releaseDateLowerBand = latestMoviesModel.releaseDateLowerBandFilterString
        releaseDateUpperBand = latestMoviesModel.releaseDateUpperBandFilterString
    }

    override fun onMovieClicked(movie: MovieDisplayItem) {
        navigatorContract.goToMovieDetail(movie)
    }

    override fun loadNextPage(page: Int): Boolean {
        if(isNotLastPage(page)) {
            view?.addLoadingItemToLatestMovies()
            getLatestMoviesUseCase.execute(LatestMoviesObserver(), buildParam(page))
            return true
        }
        return false
    }

    private fun isNotLastPage(page: Int) = page < latestMoviesModel.totalPage()

    override fun lowerBandDateClicked() {
        view?.showDatePicker(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            latestMoviesModel.releaseDateLowerBandFilter = Calendar.getInstance().apply {
                set(year, month, day)
            }
            view?.setLowerBandDateText("From: ${latestMoviesModel.releaseDateLowerBandFilterString}")
        }, latestMoviesModel.releaseDateLowerBandFilter ?: Calendar.getInstance())
    }

    override fun upperBandDateClicked() {
        view?.showDatePicker(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            latestMoviesModel.releaseDateUpperBandFilter = Calendar.getInstance().apply {
                set(year, month, day)
            }
            view?.setUpperBandDateText("To: ${latestMoviesModel.releaseDateUpperBandFilterString}")
            view?.showLoader()
            getLatestMoviesUseCase.execute(LatestMoviesObserver(), buildParam(FIRST_PAGE))
        }, latestMoviesModel.releaseDateUpperBandFilter ?: Calendar.getInstance())
    }

    override fun onError(message: String?) {
        view?.hideLoader()
        view?.showMessage(message)
    }

    override fun onSuccess(latestMovies: LatestMovies) {
        latestMoviesModel.latestMovies = latestMovies

        if(isInitialPage(latestMovies)){
            view?.hideLoader()
            view?.renderLatestMovies(latestMovies)
            return
        }

        view?.addNextPageToAdapter(latestMovies)
     }

    private fun isInitialPage(latestMovies: LatestMovies) = latestMovies.page == 1

    companion object {
        const val  FIRST_PAGE = 1
    }

    private inner class LatestMoviesObserver : DisposableObserver<LatestMovies>() {
        override fun onComplete() {
            this@LatestMoviesPresenter.view?.hideLoader()
        }

        override fun onError(e: Throwable) {
            Log.e("error", e.message)
            this@LatestMoviesPresenter.onError(e.message)
        }

        override fun onNext(latestMovies: LatestMovies) {
            this@LatestMoviesPresenter.onSuccess(latestMovies)
        }
    }

}

