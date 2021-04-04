package com.mas.popular_libraries.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import com.mas.popular_libraries.mvp.view.RepoInfoView
import moxy.MvpPresenter
import javax.inject.Inject

class RepoInfoPresenter(private val repo: GithubUserRepository) : MvpPresenter<RepoInfoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setName(repo.name)
        viewState.setDescription("Description\n${repo.description}")
        viewState.setUrl(repo.htmlUrl)
        viewState.setForkCount("Fork count: ${repo.forks_count}")
    }

    fun backClick(): Boolean {
        Log.d("my", "UserInfoPres")
        router.exit()
        return true
    }
}