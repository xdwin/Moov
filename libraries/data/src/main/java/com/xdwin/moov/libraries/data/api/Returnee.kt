package com.xdwin.data.api

import com.xdwin.data.data.Movies

interface Returnee<T> {
    fun result(resultee: (Movies) -> Unit)

}