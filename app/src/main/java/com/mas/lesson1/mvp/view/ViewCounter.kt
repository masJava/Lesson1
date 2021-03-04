package com.mas.lesson1.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ViewCounter : MvpView {
    fun setButton0Text(text: String)
    fun setButton1Text(text: String)
    fun setButton2Text(text: String)
}