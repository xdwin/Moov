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
class NowPlayingViewModel @Inject constructor(
    val repository: HomeRepository
) : BaseMovieViewModel<Movies>() {

    private var _nowPlayingMovies = MutableLiveData<BaseResult<Movies>>()
    val nowPlayingMovies: LiveData<BaseResult<Movies>> get() = _nowPlayingMovies

    fun fetchNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingMovies.postValue(BaseResult.Loading)
            val result = repository.getNowPlayingMoviesApi()
            if (result.isSuccessful) {
                _nowPlayingMovies.postValue(BaseResult.Success(result.body()))
            } else {
                _nowPlayingMovies.postValue(BaseResult.Error(result.message()))
            }
        }
    }

    override suspend fun fetchData() {
        fetchNowPlayingMovies()
    }

    override fun observeData(): LiveData<BaseResult<Movies>> {
        return nowPlayingMovies
    }
}