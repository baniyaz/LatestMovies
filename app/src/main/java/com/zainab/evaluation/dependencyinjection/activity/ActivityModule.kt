package com.zainab.evaluation.dependencyinjection.activity

import com.zainab.evaluation.presentation.base.BaseActivity
import com.zainab.evaluation.presentation.navigator.Navigator
import com.zainab.evaluation.presentation.navigator.NavigatorContract
import dagger.Module
import dagger.Provides

@Module
@ActivityScope
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    @ActivityScope
    fun navigator(): NavigatorContract {
        return Navigator(this.baseActivity)
    }
}
