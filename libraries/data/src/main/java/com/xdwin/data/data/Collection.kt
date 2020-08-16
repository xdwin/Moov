package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection(
    val id: Long,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) : Serializable