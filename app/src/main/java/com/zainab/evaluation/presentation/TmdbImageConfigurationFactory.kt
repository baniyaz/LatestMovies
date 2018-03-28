package com.zainab.evaluation.presentation

import android.net.Uri
import android.util.Log
import com.zainab.evaluation.domain.BaseUseCase
import com.zainab.evaluation.domain.GetConfigurationUseCase
import com.zainab.evaluation.domain.model.Configuration
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class TmdbImageConfigurationFactory @Inject constructor(getConfigurationUseCase: GetConfigurationUseCase) {
    var configuration: Configuration = Configuration()
    private var configurationLoaded = false

    init {
        if(!configurationLoaded) {
            Log.d("TmdbImageConfigurationFactory","Fetching Image Configuration")
            getConfigurationUseCase.execute(ConfigurationObserver(), BaseUseCase.DefaultParam())
        }
    }

    private inner class ConfigurationObserver : DisposableObserver<Configuration>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) {}

        override fun onNext(configuration: Configuration) {
            this@TmdbImageConfigurationFactory.configuration = configuration
            configurationLoaded = true
        }
    }

    fun buildPosterImageUri(url: String ): Uri {
        val s = "${configuration.baseUrl}${configuration.posterSizes[0]}$url"
        Log.d("TmdbImageConfigurationFactory",s)
        return Uri.parse(s)
    }


    fun buildBackdroprImageUri(url: String ): Uri {
        val s = "${configuration.baseUrl}${configuration.backdropSizes[0]}$url"
        Log.d("TmdbImageConfigurationFactory",s)
        return Uri.parse(s)
    }

}
