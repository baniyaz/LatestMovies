package com.zainab.evaluation.presentation.moviedetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zainab.evaluation.R
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.TmdbImageConfigurationFactory
import com.zainab.evaluation.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment : Fragment(), MovieDetailContract.View {
    @Inject lateinit var presenter: MovieDetailContract.Presenter

    @Inject lateinit var tmdbImageConfigurationFactory: TmdbImageConfigurationFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseActivity = activity as BaseActivity
        baseActivity.activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_movie_detail, container, false)
    }

    private fun retrieveMovieFromBundle(): MovieDisplayItem? {
        return arguments?.getParcelable(ITEM_ARG)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        retrieveMovieFromBundle()?.let { presenter.initialize(it) }
    }

    override fun renderMovieDetail(movie: MovieDisplayItem) {
        movie.apply {
            fragment_movie_title.text = title
            fragment_movie_overview.text = overview
            fragment_movie_release_date.text = releaseDate
            backdropPath?.let {
                val uri = tmdbImageConfigurationFactory.buildBackdroprImageUri(it)
                fragment_movie_poster.setImageURI(uri, title)
            }
        }
    }

    companion object {
        const val ITEM_ARG: String = "ITEM_ARG"

        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment()
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
        }
    }
}
