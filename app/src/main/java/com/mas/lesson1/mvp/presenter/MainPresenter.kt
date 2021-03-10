package com.mas.lesson1.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screen: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClick() {
        router.exit()
    }

}