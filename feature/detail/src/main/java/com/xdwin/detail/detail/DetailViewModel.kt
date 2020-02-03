package com.xdwin.detail.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(val useCase: DetailUseCase) : ViewModel() {
    private val _popularMovies = MutableLiveData<Response<Movie>>()
    val popularMovies: LiveData<Response<Movie>>
        get() = _popularMovies

    fun fetchPopularMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.postValue(useCase.getMovieDetail(movieId))
        }
    }
}