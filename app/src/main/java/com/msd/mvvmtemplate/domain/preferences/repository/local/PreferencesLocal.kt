package com.msd.mvvmtemplate.domain.preferences.repository.local

import android.content.SharedPreferences
import com.msd.mvvmtemplate.utils.Constants.Companion.SAVED_DATA
import javax.inject.Inject

class PreferencesLocal @Inject constructor() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun getSavedData(): Int = sharedPreferences.getInt(SAVED_DATA, 0)

    fun setSavedData(dataToSave: Int) =
        sharedPreferences.edit().putInt(SAVED_DATA, dataToSave).apply()

}