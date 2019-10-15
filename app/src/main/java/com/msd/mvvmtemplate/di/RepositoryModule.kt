package com.msd.mvvmtemplate.di

import android.content.Context
import android.content.SharedPreferences
import com.msd.mvvmtemplate.utils.Constants.Companion.PREFERENCES_NAME
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

}