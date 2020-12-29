package com.xdwin.home.search

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.SearchService
import com.xdwin.data.data.Movies
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {
    override suspend fun searchMovies(query: String, page: Int): Response<Movies> {
        return ApiCreator.retrofit
            .create(SearchService::class.java)
            .searchMovies(query = query, page = page)
            .awaitResponse()
    }
}