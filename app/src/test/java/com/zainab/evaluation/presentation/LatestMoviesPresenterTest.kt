package com.zainab.evaluation.presentation

import com.nhaarman.mockito_kotlin.*
import com.zainab.evaluation.domain.GetLatestMoviesUseCase
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.model.MovieDisplayItem
import com.zainab.evaluation.presentation.latestmovies.LatestMoviesContract
import com.zainab.evaluation.presentation.latestmovies.LatestMoviesPresenter
import com.zainab.evaluation.presentation.navigator.NavigatorContract
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString


@RunWith(JUnit4::class)
class LatestMoviesPresenterTest {

    private lateinit var mockLatestMoviesView: LatestMoviesContract.View
    private lateinit var mockLatestMoviesPresenter: LatestMoviesPresenter
    private lateinit var mockGetLatestMovies: GetLatestMoviesUseCase
    private lateinit var navigatorContract: NavigatorContract
    private lateinit var mockLatestMoviesModel: LatestMoviesContract.Model
    
    private lateinit var captor: KArgumentCaptor<DisposableObserver<LatestMovies>>
    private lateinit var  latestMoviesCaptor: KArgumentCaptor<GetLatestMoviesUseCase.LatestMovieParam>

    @Before
    fun setup() {
        captor = argumentCaptor()
        latestMoviesCaptor = argumentCaptor()
        mockLatestMoviesView = mock()
        mockGetLatestMovies = mock()
        navigatorContract = mock()
        mockLatestMoviesModel = mock()
        mockLatestMoviesPresenter = LatestMoviesPresenter(mockGetLatestMovies, navigatorContract, mockLatestMoviesModel)
        mockLatestMoviesPresenter.attachView(mockLatestMoviesView)
    }

    @Test
    fun retrieveLatestMovies() {
        mockLatestMoviesPresenter.getLatestMovies()

        verify(mockLatestMoviesView).setUpperBandDateText(anyString())
        verify(mockLatestMoviesView).setLowerBandDateText(anyString())
        verify(mockLatestMoviesView).showLoader()

        verify(mockGetLatestMovies).execute(captor.capture(), latestMoviesCaptor.capture())
        captor.firstValue.onNext(getFirstPageLatestMovies())

        verify(mockLatestMoviesView).hideLoader()
        verify(mockLatestMoviesView).renderLatestMovies(any())
    }

    @Test
    fun retrieveNextPageLatestMovies() {
        given(mockLatestMoviesModel.totalPage()).willReturn(3)
        mockLatestMoviesPresenter.loadNextPage(2)

        verify(mockGetLatestMovies).execute(captor.capture(),latestMoviesCaptor.capture())
        captor.firstValue.onNext(getNextPageLatestMovies())

        verify(mockLatestMoviesView).addLoadingItemToLatestMovies()
        verify(mockLatestMoviesView).addNextPageToAdapter(any())
        verify(mockLatestMoviesModel).totalPage()
    }

    private fun getFirstPageLatestMovies() = LatestMovies().apply {
        results = mutableListOf(getHappyMovie(), getSecondHappyMovie())
        totalPages = 992
        page = 1
    }

    private fun getNextPageLatestMovies() = LatestMovies().apply {
        results = mutableListOf(getHappyMovie(), getSecondHappyMovie())
        totalPages = 992
        page = 2
    }

    private fun getHappyMovie() = MovieDisplayItem(
            id = 1,
            posterPath = "/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg\"" ,
            backdropPath = "/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg",
            overview = "Dont Stop me now" ,
            releaseDate = "2015-05-05",
            title = "I am a Engineer" )

    private fun getSecondHappyMovie() = MovieDisplayItem(
            id = 2,
            posterPath = "/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg\"" ,
            backdropPath = "/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg",
            overview = "Second Happy Movie" ,
            releaseDate = "2015-05-05",
            title = "I am a Engineer")
}