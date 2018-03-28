package com.zainab.evaluation.domain

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.zainab.evaluation.domain.executor.PostExecutionThread
import com.zainab.evaluation.domain.executor.ThreadExecutor
import com.zainab.evaluation.domain.repository.TmdbRepository
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetLatestMoviesUseCaseTest {

    @Mock
    lateinit var tmdbRepository: TmdbRepository
    @Mock
    lateinit var threadExecutor: ThreadExecutor
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getLatestMoviesUseCase: GetLatestMoviesUseCase

    @Before
    fun setUp() {
        getLatestMoviesUseCase = GetLatestMoviesUseCase(tmdbRepository, threadExecutor, postExecutionThread)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun shouldGetLatestMovies(){
        getLatestMoviesUseCase.buildUseCaseObservable(getFakeParam())

        verify(tmdbRepository).latestMovies(anyInt(), anyString(), anyString(), anyString())
        verifyNoMoreInteractions(tmdbRepository)
        verifyZeroInteractions(threadExecutor)
        verifyZeroInteractions(postExecutionThread)
    }

    private fun getFakeParam() = GetLatestMoviesUseCase.LatestMovieParam().apply {
        page = anyInt()
        releaseDateLowerBand = anyString()
        releaseDateUpperBand = anyString()
        sortBy = anyString()
    }
}