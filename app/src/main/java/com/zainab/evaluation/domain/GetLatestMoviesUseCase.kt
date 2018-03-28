package com.zainab.evaluation.domain

import com.zainab.evaluation.domain.executor.PostExecutionThread
import com.zainab.evaluation.domain.executor.ThreadExecutor
import com.zainab.evaluation.domain.model.LatestMovies
import com.zainab.evaluation.domain.repository.TmdbRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(val tmdbRepository: TmdbRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread)
    : BaseUseCase<LatestMovies, GetLatestMoviesUseCase.LatestMovieParam>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(param: LatestMovieParam): Observable<LatestMovies> {
        return tmdbRepository.latestMovies(param.page, param.releaseDateLowerBand, param.releaseDateUpperBand, param.sortBy)
    }

    class LatestMovieParam: BaseUseCase.Param{
        var page: Int = 0
        var releaseDateLowerBand: String? = null
        var releaseDateUpperBand: String? = null
        var sortBy = "popularity.desc"
    }
}
