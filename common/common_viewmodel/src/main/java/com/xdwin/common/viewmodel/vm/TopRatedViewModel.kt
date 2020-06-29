package com.xdwin.common.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xdwin.common.viewmodel.HomeRepository
import com.xdwin.common.viewmodel.usecase.HomeUseCase
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class TopRatedViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    val topRatedMovies: LiveData<BaseResult<Movies>>
        get() = fetchTopRatedMovies()

    private fun fetchTopRatedMovies(): LiveData<BaseResult<Movies>> {
        val _topRatedMovies = MutableLiveData<BaseResult<Movies>>()

        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.postValue(BaseResult.Loading)
            val result = repository.getTopRatedMoviesApi()
            if (result.isSuccessful) {
                _topRatedMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _topRatedMovies.postValue(BaseResult.Error(result.message()))
            }
        }
        return _topRatedMovies
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return topRatedMovies
    }
}