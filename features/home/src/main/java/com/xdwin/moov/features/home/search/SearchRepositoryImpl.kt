package com.xdwin.moov.features.home.search

import com.xdwin.data.api.ApiCreator
import com.xdwin.data.api.service.SearchService
import com.xdwin.data.data.Movies
import retrofit2.Response
import retrofit2.awaitResponse

class SearchRepositoryImpl : SearchRepository {
    override suspend fun searchMovies(query: String, page: Int): Response<Movies> {
        return ApiCreator.retrofit
            .create(SearchService::class.java)
            .searchMovies(query = query, page = page)
            .awaitResponse()
    }
}