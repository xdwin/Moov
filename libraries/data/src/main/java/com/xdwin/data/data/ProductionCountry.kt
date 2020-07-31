package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    val name: String
) : Serializable