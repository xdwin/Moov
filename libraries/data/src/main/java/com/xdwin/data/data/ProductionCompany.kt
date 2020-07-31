package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path")
    val logo_path: String,
    val name: String,
    @SerializedName("origin_country")
    val origin_country: String
) : Serializable