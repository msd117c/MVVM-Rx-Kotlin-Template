package com.msd.mvvmtemplate.di

import android.app.Activity
import android.app.Application
import android.content.Context
import com.msd.mvvmtemplate.base.BaseApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MVVMTemplateApplication : Application(), HasActivityInjector, BaseApplication {

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun getContext(): Context = this

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}