package com.zainab.evaluation.dependencyinjection.app

import com.zainab.evaluation.dependencyinjection.activity.ActivityComponent
import com.zainab.evaluation.dependencyinjection.activity.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (TmdbApiModule::class)])
interface AppComponent {
    fun plusActivityComponent(activityModule: ActivityModule): ActivityComponent
}
