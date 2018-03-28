package com.zainab.evaluation.presentation.latestmovies

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zainab.evaluation.R
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.extension.hide
import com.zainab.evaluation.extension.show
import com.zainab.evaluation.extension.showSnack
import com.zainab.evaluation.presentation.TmdbImageConfigurationFactory
import com.zainab.evaluation.presentation.base.BaseActivity
import com.zainab.evaluation.presentation.latestmovies.LatestMoviesAdapter.MovieItemClickListener
import kotlinx.android.synthetic.main.fragment_latest_movies.*
import java.util.*
import javax.inject.Inject

class LatestMoviesFragment : Fragment(), LatestMoviesContract.View {
    @Inject lateinit var presenter: LatestMoviesContract.Presenter

    @Inject lateinit var tmdbImageConfigurationFactory: TmdbImageConfigurationFactory

    private var latestMoviesAdapter: LatestMoviesAdapter = LatestMoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseActivity = activity as BaseActivity
        baseActivity.activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_latest_movies, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews()
        preparePresenter()
    }

    private fun preparePresenter() {
        presenter.attachView(this)
        presenter.initialize()
    }

    private fun prepareViews() {
        latest_movies_list?.adapter = latestMoviesAdapter
        latest_movies_list.addOnScrollListener(InfiniteScrollListener(presenter))
        latestMoviesAdapter.setOnItemClickListener(object: MovieItemClickListener {
            override fun onItemClick(movie: MovieDisplayItem?) {
                movie?.let {
                    presenter.onMovieClicked(it)
                }
            }
        })
        latestMoviesAdapter.setTmdbImageConfigurationFactory(tmdbImageConfigurationFactory)

        latest_movies_upperband_date.setOnClickListener { _ ->  presenter.upperBandDateClicked()}
        latest_movies_lowerband_date.setOnClickListener { _ ->  presenter.lowerBandDateClicked()}
    }

    override fun showDatePicker(onDateSetListener: DatePickerDialog.OnDateSetListener, currentDate: Calendar) {
        DatePickerDialog(activity, onDateSetListener,
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)).apply {

        }.show()
    }

    override fun renderLatestMovies(latestMovies: LatestMovies) {
        latestMoviesAdapter.setLatestMovies(latestMovies)
    }

    override fun addNextPageToAdapter(latestMovies: LatestMovies) {
        latestMoviesAdapter.addNextPage(latestMovies)
    }

    override fun addLoadingItemToLatestMovies() {
        latestMoviesAdapter.addLoadingItem()
    }

    override fun setLowerBandDateText(s: String) {
        latest_movies_lowerband_date.text = s
    }

    override fun setUpperBandDateText(s: String) {
        latest_movies_upperband_date.text = s
    }

    override fun hideLoader() {
        latest_movies_progressBar.hide()
    }

    override fun showLoader() {
        latest_movies_progressBar.show()
    }

    override fun showMessage(message: String?) {
        activity?.showSnack(message, latest_movies_list)
    }

    companion object {
        fun newInstance(): LatestMoviesFragment {
            return LatestMoviesFragment()
        }
    }
}
