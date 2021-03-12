package com.mas.lesson1.mvp.navigation

import com.github.terrakok.cicerone.Screen
import com.mas.lesson1.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun userInfo(user: GithubUser): Screen
}