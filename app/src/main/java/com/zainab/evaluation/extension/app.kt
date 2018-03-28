package com.zainab.evaluation.extension

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

fun Activity.showSnack(msg: CharSequence?, view: View) {
    msg?.let {
        Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
    }
}

