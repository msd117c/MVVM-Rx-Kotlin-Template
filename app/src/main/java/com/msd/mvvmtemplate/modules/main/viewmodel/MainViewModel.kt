package com.msd.mvvmtemplate.modules.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.msd.mvvmtemplate.base.domain.SubscribeWrapper
import com.msd.mvvmtemplate.base.viewmodel.BaseViewModel
import com.msd.mvvmtemplate.domain.preferences.repository.PreferencesRepository
import com.msd.mvvmtemplate.modules.main.navigator.MainNavigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<MainNavigator>() {

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    val data = MutableLiveData<String>()

    fun initViewModel() {
        compositeDisposable.add(preferencesRepository.getSavedData()
            .flatMap { savedData ->
                data.postValue("Count: $savedData")
                Observable.just(savedData)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(SubscribeWrapper<Any>(state)))
    }

    fun incrementCounter() = compositeDisposable.add(preferencesRepository.getSavedData()
        .flatMap { savedData ->
            preferencesRepository.setSavedData(savedData + 1)
                .flatMap {
                    initViewModel()
                    Observable.just(it)
                }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(SubscribeWrapper<Any>(state)))
}