package com.mas.lesson1.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.model.entity.GithubUserRepos

interface IScreens {
    fun users(): Screen
    fun userInfo(user: GithubUser): Screen
    fun repoInfo(repo: GithubUserRepos): Screen
}