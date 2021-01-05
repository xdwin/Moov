package com.xdwin.moov.features.detail.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Credits
import com.xdwin.data.data.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(val repo: DetailRepository) : ViewModel() {

    private var _detailMovie = MutableLiveData<BaseResult<MovieDetail>>()
    val detailMovie: LiveData<BaseResult<MovieDetail>> get() = _detailMovie

    private var _credits = MutableLiveData<BaseResult<Credits>>()
    val credits: LiveData<BaseResult<Credits>> get() = _credits

    fun fetchPopularMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailMovie.postValue(BaseResult.Loading)

            val result = repo.getMovieDetail(movieId)
            if (result.isSuccessful) {
                _detailMovie.postValue(BaseResult.Success(result.body()))
            } else {
                _detailMovie.postValue(BaseResult.Error(result.message()))
            }
        }
    }

    fun fetchCredits(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getCredits(movieId)
            if (result.isSuccessful) {
                _credits.postValue(BaseResult.Success(result.body()))
            } else {
                _credits.postValue(BaseResult.Error(result.message()))
            }
        }
    }
}