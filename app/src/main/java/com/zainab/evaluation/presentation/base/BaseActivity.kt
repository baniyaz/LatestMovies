package com.zainab.evaluation.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.zainab.evaluation.App
import com.zainab.evaluation.R
import com.zainab.evaluation.dependencyinjection.activity.ActivityComponent
import com.zainab.evaluation.dependencyinjection.activity.ActivityModule
import com.zainab.evaluation.dependencyinjection.app.AppComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intialiseComponent()
    }

    private fun intialiseComponent() {
        activityComponent = getAppComponent().plusActivityComponent(ActivityModule(this))
    }

    private fun getAppComponent(): AppComponent {
        return (application as App).appComponent
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_framelayout, fragment, fragment.javaClass.name)
                .apply { if (addToBackStack) addToBackStack(null) }
                .commitAllowingStateLoss()
    }

     fun pushFragmentToBackStack(fragment: Fragment) {
        pushFragment(fragment, true)
    }



}
