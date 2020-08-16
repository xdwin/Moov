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

class PopularViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    val popularMovies: LiveData<BaseResult<Movies>>
        get() = fetchPopularMovies()

    private fun fetchPopularMovies(): LiveData<BaseResult<Movies>> {
        val _popularMovies = MutableLiveData<BaseResult<Movies>>()
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.postValue(BaseResult.Loading)
            val result = repository.getPopularMoviesApi()
            if (result.isSuccessful) {
                _popularMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _popularMovies.postValue(BaseResult.Error(result.message()))
            }
        }
        return _popularMovies
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return popularMovies
    }
}