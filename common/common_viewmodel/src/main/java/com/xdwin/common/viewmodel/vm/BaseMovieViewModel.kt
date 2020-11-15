package com.xdwin.common.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xdwin.data.api.BaseResult

abstract class BaseMovieViewModel<T : Any> : ViewModel() {
    abstract fun fetchData()
    abstract fun observeData(): LiveData<BaseResult<T>>
}