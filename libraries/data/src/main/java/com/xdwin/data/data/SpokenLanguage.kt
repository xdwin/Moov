package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso_639_1: String,
    val name: String
) : Serializable