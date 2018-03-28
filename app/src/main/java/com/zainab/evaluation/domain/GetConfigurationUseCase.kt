package com.zainab.evaluation.domain

import com.zainab.evaluation.domain.executor.PostExecutionThread
import com.zainab.evaluation.domain.executor.ThreadExecutor
import com.zainab.evaluation.domain.model.Configuration
import com.zainab.evaluation.domain.repository.TmdbRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(val tmdbRepository: TmdbRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread)
    : BaseUseCase<Configuration, BaseUseCase.Param>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(param: BaseUseCase.Param): Observable<Configuration> {
       return tmdbRepository.getConfiguration()
    }
}
