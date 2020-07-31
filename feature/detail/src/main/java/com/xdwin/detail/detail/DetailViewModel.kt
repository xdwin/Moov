package com.xdwin.detail.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class DetailViewModel @Inject constructor(val repo: DetailRepository) : ViewModel() {
    var detailMovie = MutableLiveData<BaseResult<MovieDetail>>()
    private set

    fun fetchPopularMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            detailMovie.postValue(BaseResult.Loading)

            val result = repo.getMovieDetail(movieId)
            if (result.isSuccessful) {
                detailMovie.postValue(BaseResult.Success(result.body()))
            } else {
                detailMovie.postValue(BaseResult.Error(result.message()))
            }
        }
    }
}