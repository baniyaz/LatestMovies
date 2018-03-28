package com.zainab.evaluation

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.zainab.evaluation.dependencyinjection.app.AppComponent
import com.zainab.evaluation.dependencyinjection.app.AppModule
import com.zainab.evaluation.dependencyinjection.app.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        intialiseAppComponent()
        Fresco.initialize(this)
    }

    private fun intialiseAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}