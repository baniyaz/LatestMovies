package com.zainab.evaluation

import android.os.Bundle
import com.zainab.evaluation.presentation.base.BaseActivity
import com.zainab.evaluation.presentation.latestmovies.LatestMoviesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushFragment(LatestMoviesFragment.newInstance(), false)
    }
}
