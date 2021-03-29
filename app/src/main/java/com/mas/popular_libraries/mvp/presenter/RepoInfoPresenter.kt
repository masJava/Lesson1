package com.mas.popular_libraries.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos
import com.mas.popular_libraries.mvp.view.RepoInfoView
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