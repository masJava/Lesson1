package com.mas.popular_libraries.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos

interface IScreens {
    fun users(): Screen
    fun userInfo(user: GithubUser): Screen
    fun repoInfo(repo: GithubUserRepos): Screen
}