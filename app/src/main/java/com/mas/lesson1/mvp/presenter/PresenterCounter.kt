package com.mas.lesson1.mvp.presenter

import com.mas.lesson1.mvp.model.ModelCounters
import com.mas.lesson1.mvp.view.ViewCounter

class PresenterCounter(private val view: ViewCounter) {
    private val model = ModelCounters()

    fun counter0Click() {
        val index = 0
        view.setButtonText(index, model.next(index).toString())
    }

    fun counter1Click() {
        val index = 1
        view.setButtonText(index, model.next(index).toString())
    }

    fun counter2Click() {
        val index = 2
        view.setButtonText(index, model.next(index).toString())
    }
}