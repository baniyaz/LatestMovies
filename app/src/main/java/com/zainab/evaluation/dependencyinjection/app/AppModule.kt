package com.zainab.evaluation.dependencyinjection.app

import android.content.Context
import com.zainab.evaluation.App
import com.zainab.evaluation.data.executor.JobExecutor
import com.zainab.evaluation.domain.executor.PostExecutionThread
import com.zainab.evaluation.domain.executor.ThreadExecutor
import com.zainab.evaluation.presentation.executor.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Singleton
class AppModule constructor(val app: App) {
    @Provides
    @Singleton
    fun provideContext():Context{
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideExecutionThread():ThreadExecutor = JobExecutor()

    @Provides
    @Singleton
    fun providePostExecutionThread():PostExecutionThread = UiThread()
}