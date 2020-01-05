package com.xdwin.base.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xdwin.base.data.Movies

class HomeViewModel : ViewModel() {
    private var popularMovies = HomeRepository.getPopularMovies()

    fun getPopularMovies(): LiveData<Movies> {
        return popularMovies
    }
}