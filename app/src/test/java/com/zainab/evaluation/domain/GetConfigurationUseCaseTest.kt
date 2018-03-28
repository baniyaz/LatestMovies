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
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetConfigurationUseCaseTest {

    @Mock
    lateinit var tmdbRepository: TmdbRepository
    @Mock
    lateinit var threadExecutor: ThreadExecutor
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getConfigurationUseCase: GetConfigurationUseCase

    @Before
    fun setUp() {
        getConfigurationUseCase = GetConfigurationUseCase(tmdbRepository, threadExecutor, postExecutionThread)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun shouldGetConfiguration(){
        getConfigurationUseCase.buildUseCaseObservable(getFakeParam())

        verify(tmdbRepository).getConfiguration()
        verifyNoMoreInteractions(tmdbRepository)
        verifyZeroInteractions(threadExecutor)
        verifyZeroInteractions(postExecutionThread)
    }

    private fun getFakeParam() = GetLatestMoviesUseCase.LatestMovieParam()
}