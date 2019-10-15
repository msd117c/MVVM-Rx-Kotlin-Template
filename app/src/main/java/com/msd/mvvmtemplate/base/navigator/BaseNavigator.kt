package com.msd.mvvmtemplate.base.navigator

import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

abstract class BaseNavigator {
    lateinit var activityRef: WeakReference<AppCompatActivity>

    fun setActivity(activity: AppCompatActivity) {
        activityRef = WeakReference(activity)
    }

}