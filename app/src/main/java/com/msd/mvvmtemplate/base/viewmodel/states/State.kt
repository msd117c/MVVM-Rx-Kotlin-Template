package com.msd.mvvmtemplate.base.viewmodel.states

open class State {
    class Loading : State()
    data class Error<T : Any>(var type: T) : State()
    data class Successful<T : Any>(var success: T) : State()
}