package com.xdwin.common.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.Movies
import retrofit2.Response

abstract class BaseMovieViewModel<T : Any> : ViewModel() {
    abstract fun observeData(): LiveData<BaseResult<T>>
}