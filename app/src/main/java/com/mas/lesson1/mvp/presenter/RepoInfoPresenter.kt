package com.mas.lesson1.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.model.entity.GithubUserRepos
import com.mas.lesson1.mvp.view.RepoInfoView
import moxy.MvpPresenter

class RepoInfoPresenter(
    private val repo: GithubUserRepos,
    private val router: Router,
) :
    MvpPresenter<RepoInfoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRepoInfo(repo)
    }

    fun backClick(): Boolean {
        Log.d("my", "UserInfoPres")
        router.exit()
        return true
    }
}