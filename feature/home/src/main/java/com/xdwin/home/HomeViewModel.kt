package com.xdwin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val useCase: HomeUseCase) : ViewModel() {
    val popularMovies: LiveData<Response<Movies>>
        get() = fetchPopularMovies()

    fun fetchPopularMovies(): LiveData<Response<Movies>> {
        val _popularMovies = MutableLiveData<Response<Movies>>()
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.postValue(useCase.getPopularMovies())
        }
        return _popularMovies
    }
}