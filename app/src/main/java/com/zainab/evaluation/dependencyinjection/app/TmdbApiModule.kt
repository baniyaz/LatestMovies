package com.zainab.evaluation.dependencyinjection.app

import com.zainab.evaluation.data.remote.TmdbRepositoryImpl
import com.zainab.evaluation.data.remote.TmdbService
import com.zainab.evaluation.data.remote.TmdbServiceFactory
import com.zainab.evaluation.domain.GetConfigurationUseCase
import com.zainab.evaluation.domain.repository.TmdbRepository
import com.zainab.evaluation.presentation.TmdbImageConfigurationFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class TmdbApiModule {
    @Provides
    @Singleton
    fun provideTmdbRepository(tmdbService: TmdbService): TmdbRepository = TmdbRepositoryImpl(tmdbService)

    @Provides
    @Singleton
    fun provideTmdbService() = TmdbServiceFactory.buildTmdbService(TmdbService.API_URL)

    @Provides
    @Singleton
    fun provideTmdbImageConfiguration(getConfigurationUseCase: GetConfigurationUseCase) = TmdbImageConfigurationFactory(getConfigurationUseCase)
}