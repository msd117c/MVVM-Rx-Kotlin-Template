package com.msd.mvvmtemplate.domain.preferences.repository

import com.msd.mvvmtemplate.domain.preferences.repository.local.PreferencesLocal
import io.reactivex.Observable
import javax.inject.Inject

class PreferencesRepository @Inject constructor() {

    @Inject
    lateinit var preferencesLocal: PreferencesLocal

    fun getSavedData(): Observable<Int> = Observable.just(
        preferencesLocal.getSavedData()
    )

    fun setSavedData(dataToSave: Int): Observable<Unit> = Observable.just(
        preferencesLocal.setSavedData(dataToSave)
    )
}