package com.xdwin.data.data

import java.io.Serializable

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
) : Serializable