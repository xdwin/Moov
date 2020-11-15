package com.xdwin.data.data

import com.google.gson.annotations.SerializedName

data class Movies(
    var results: List<Movie> = listOf(),
    var page: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0
)