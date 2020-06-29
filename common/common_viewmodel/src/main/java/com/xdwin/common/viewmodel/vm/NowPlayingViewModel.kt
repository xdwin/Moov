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

class NowPlayingViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    val nowPlayingMovies: LiveData<BaseResult<Movies>>
        get() = fetchNowPlayingMovies()

    fun fetchNowPlayingMovies(): LiveData<BaseResult<Movies>> {
        val _nowPlayingMovies = MutableLiveData<BaseResult<Movies>>()
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingMovies.postValue(BaseResult.Loading)
            val result = repository.getNowPlayingMoviesApi()
            if (result.isSuccessful) {
                _nowPlayingMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _nowPlayingMovies.postValue(BaseResult.Error(result.message()))
            }
        }
        return _nowPlayingMovies
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return nowPlayingMovies
    }
}