package com.xdwin.common.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdwin.common.viewmodel.HomeRepository
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    private var _topRatedMovies = MutableLiveData<BaseResult<Movies>>()
    val topRatedMovies: LiveData<BaseResult<Movies>> get() = _topRatedMovies

    private fun fetchTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.postValue(BaseResult.Loading)
            val result = repository.getTopRatedMoviesApi()
            if (result.isSuccessful) {
                _topRatedMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _topRatedMovies.postValue(BaseResult.Error(result.message()))
            }
        }
    }

    override suspend fun fetchData() {
        fetchTopRatedMovies()
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return topRatedMovies
    }
}