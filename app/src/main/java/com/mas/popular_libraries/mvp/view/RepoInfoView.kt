package com.mas.popular_libraries.mvp.view

import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoInfoView : MvpView {
    fun setRepoInfo(repo: GithubUserRepos)
}