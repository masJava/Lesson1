package com.mas.lesson1.mvp.model

class ModelCounters {
    private val counters = mutableListOf(0, 0, 0)

    fun next(index: Int): Int {
        counters[index]++
        return counters[index]
    }
}