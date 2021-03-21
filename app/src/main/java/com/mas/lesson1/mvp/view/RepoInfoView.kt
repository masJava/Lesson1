package com.mas.lesson1.mvp.view

import com.mas.lesson1.mvp.model.entity.GithubUserRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoInfoView : MvpView {
    fun setRepoInfo(repo: GithubUserRepos)
}