package com.msd.mvvmtemplate.base.domain

import com.msd.mvvmtemplate.base.viewmodel.states.Error

data class TaskException constructor(var error: Error) : Throwable()