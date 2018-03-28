package com.zainab.evaluation.domain

import com.zainab.evaluation.domain.executor.PostExecutionThread
import com.zainab.evaluation.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * This is a base request to be implemented by all requests. It contains how requests are executed and disposed.
 * It returns a [Observable].
 */
abstract class BaseUseCase<T, in Param> constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {
    private val disposables = CompositeDisposable()

    /**
     * Builds a [Observable] which will be used when the current [UseCase] is executed.
     */
    abstract fun buildUseCaseObservable(param: Param): Observable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(observer: DisposableObserver<T>, param: Param) {
        val observable = this.buildUseCaseObservable(param)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Observable<T>
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    interface Param
    class DefaultParam:Param
}
