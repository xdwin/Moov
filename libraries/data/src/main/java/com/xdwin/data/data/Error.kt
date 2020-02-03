package com.xdwin.data.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Error(
    @SerializedName("status_message")
    var statusMessage: String,
    var success: Boolean,
    @SerializedName("status_code")
    var statusCode: Int
) : Serializable