package com.xdwin.common.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdwin.common.viewmodel.HomeRepository
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    val featuredPopularMovie: LiveData<BaseResult<Movie>> get() = _featuredPopularMovie
    private var _featuredPopularMovie = MutableLiveData<BaseResult<Movie>>()

    val popularMovies: LiveData<BaseResult<Movies>> get() = _popularMovies
    private var _popularMovies = MutableLiveData<BaseResult<Movies>>()

    fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.postValue(BaseResult.Loading)
            _featuredPopularMovie.postValue(BaseResult.Loading)
            val result = repository.getPopularMoviesApi()
            if (result.isSuccessful) {
                _popularMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _popularMovies.postValue(BaseResult.Error(result.message()))
            }
        }
    }

    override suspend fun fetchData() {
        fetchPopularMovies()
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return popularMovies
    }
}