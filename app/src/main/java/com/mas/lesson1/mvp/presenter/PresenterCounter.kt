package com.mas.lesson1.mvp.presenter

import com.mas.lesson1.mvp.model.ModelCounters
import com.mas.lesson1.mvp.view.ViewCounter
import moxy.MvpPresenter

class PresenterCounter(private val model: ModelCounters) : MvpPresenter<ViewCounter>() {

    fun counter0Click() {
        viewState.setButton0Text(model.next(0).toString())
    }

    fun counter1Click() {
        viewState.setButton1Text(model.next(1).toString())
    }

    fun counter2Click() {
        viewState.setButton2Text(model.next(2).toString())
    }
}