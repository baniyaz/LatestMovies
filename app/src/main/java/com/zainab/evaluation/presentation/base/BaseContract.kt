package com.zainab.evaluation.presentation.base

/**
 * The represents the basic contract all views and Presenters must respect
 */
class BaseContract {
    interface Presenter<in T : View> {
        /**
         * To be called by the view when the android View/Activity is ready for use. If the view is a Fragment,
         * it's to be called after the views after views inflation in onCreateView. If the view is an Activity,
         * it can be called after views inflation in onCreate()
         *
         * @param view The view.
         */
        fun attachView(view: T)

        /**
         * All Observers should be disposed at this point
         */
        fun destroy()
    }

    interface View
}
