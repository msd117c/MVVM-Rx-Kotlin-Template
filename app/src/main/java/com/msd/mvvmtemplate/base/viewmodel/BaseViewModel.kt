package com.msd.mvvmtemplate.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msd.mvvmtemplate.base.navigator.BaseNavigator
import com.msd.mvvmtemplate.base.viewmodel.states.State
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    @Inject
    lateinit var navigator: N

    open val state = MutableLiveData<State>()
    val ads = MutableLiveData<Boolean>()

    protected val compositeDisposable = CompositeDisposable()

    fun resumeViewModel() {

    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

}