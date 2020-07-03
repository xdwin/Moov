package com.xdwin.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {
    private val _searchResult: MutableLiveData<BaseResult<Movies>> = MutableLiveData()
    val searchResult: LiveData<BaseResult<Movies>> = _searchResult

    fun searchMovies(query: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchResult.postValue(BaseResult.Loading)
            if (query.isBlank()) {
                _searchResult.postValue(BaseResult.Success(
                    Movies(
                        results = emptyList(),
                        page = 1,
                        totalPages = 1,
                        totalResults = 0
                    )
                ))
                return@launch
            }

            val result = repository.searchMovies(query, page)
            if (result.isSuccessful) {
                _searchResult.postValue(BaseResult.Success(result.body()))
            } else {
                _searchResult.postValue(BaseResult.Error(result.message()))
            }
        }
    }
}