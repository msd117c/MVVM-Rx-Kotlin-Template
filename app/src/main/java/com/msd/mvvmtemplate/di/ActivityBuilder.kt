package com.msd.mvvmtemplate.di

import com.msd.mvvmtemplate.modules.main.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

}