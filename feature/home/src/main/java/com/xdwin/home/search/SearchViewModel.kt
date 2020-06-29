package com.xdwin.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdwin.data.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {
    private val _searchResult: MutableLiveData<Response<Movies>> = MutableLiveData()
    val searchResult: LiveData<Response<Movies>> = _searchResult

    fun searchMovies(query: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchMovies(query, page)
            _searchResult.postValue(result)
        }
    }
}