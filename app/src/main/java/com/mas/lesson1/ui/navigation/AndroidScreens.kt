package com.mas.lesson1.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.ui.fragment.UserInfoFragment
import com.mas.lesson1.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun userInfo(user: GithubUser) = FragmentScreen {
        UserInfoFragment.newInstance(user)
    }
}