package com.xdwin.base.data

import com.google.gson.annotations.SerializedName

data class Movies(
    var results: List<Movie>,
    var page: Int,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int
)