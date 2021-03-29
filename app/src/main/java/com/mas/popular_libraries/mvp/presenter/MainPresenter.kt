package com.mas.popular_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.mas.popular_libraries.mvp.navigation.IScreens
import com.mas.popular_libraries.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClick() {
        router.exit()
    }

}