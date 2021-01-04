package com.xdwin.data.api

import java.io.Serializable

data class BaseResponse<T>(
    var data: T
) : Serializable