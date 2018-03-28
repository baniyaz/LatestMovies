package com.zainab.evaluation.presentation

import com.nhaarman.mockito_kotlin.*
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.moviedetail.MovieDetailContract
import com.zainab.evaluation.presentation.moviedetail.MovieDetailPresenter
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MovieDetailPresenterTest {

    private lateinit var mockMovieDetailView: MovieDetailContract.View
    private lateinit var mockMovieDetailPresenter: MovieDetailPresenter

    private lateinit var captor: KArgumentCaptor<DisposableObserver<MovieDisplayItem>>

    @Before
    fun setup() {
        captor = argumentCaptor()
        mockMovieDetailView = mock()
        mockMovieDetailPresenter = MovieDetailPresenter()
        mockMovieDetailPresenter.attachView(mockMovieDetailView)
    }

    @Test
    fun showMovieDetail() {
        mockMovieDetailPresenter.initialize(getHappyMovie())

        verify(mockMovieDetailView).renderMovieDetail(any())
    }

    private fun getHappyMovie() = MovieDisplayItem(
            id = 1,
            posterPath = "/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg\"",
            backdropPath = "/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg",
            overview = "Dont Stop me now",
            releaseDate = "2015-05-05",
            title = "I am a Engineer")
}