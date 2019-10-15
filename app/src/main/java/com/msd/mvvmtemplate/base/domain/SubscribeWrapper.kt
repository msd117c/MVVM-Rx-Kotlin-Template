package com.msd.mvvmtemplate.base.domain

import androidx.lifecycle.MutableLiveData
import com.msd.mvvmtemplate.base.viewmodel.states.Error
import com.msd.mvvmtemplate.base.viewmodel.states.State
import io.reactivex.observers.DisposableObserver

class SubscribeWrapper<T : Any> constructor(private val state: MutableLiveData<State>?) :
    DisposableObserver<T>() {

    override fun onError(throwable: Throwable) {
        state?.postValue(State.Error(Error.UNKNOWN_ERROR))
    }

    override fun onComplete() {}

    override fun onNext(t: T) {}

}