package com.xdwin.abstraction

import kotlin.random.Random

object Randomizer {
    private val random by lazy { Random(100) }

    fun generateNextInt(from: Int = 0, to: Int = Int.MAX_VALUE): Int {
        return random.nextInt(from, to)
    }
}