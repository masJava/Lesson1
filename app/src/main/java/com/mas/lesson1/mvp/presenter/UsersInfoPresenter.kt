package com.mas.lesson1.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.view.UsersInfoView
import moxy.MvpPresenter

class UsersInfoPresenter(private val user: GithubUser, private val router: Router) :
    MvpPresenter<UsersInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }


    fun backClick(): Boolean {
        router.exit()
        Log.d("my", "backUserInfo")
        return true
    }
}