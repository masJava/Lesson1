package com.mas.lesson1.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClick() {
        router.exit()
        Log.d("my", "backMain")
    }

}