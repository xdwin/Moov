package com.xdwin.base.api

import com.xdwin.base.data.Movies

interface Returnee<T> {
    fun result(resultee: (Movies) -> Unit)

}