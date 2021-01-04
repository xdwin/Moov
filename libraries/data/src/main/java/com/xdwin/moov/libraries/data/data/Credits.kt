package com.xdwin.data.data

import java.io.Serializable

data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
) : Serializable