package com.mas.lesson1.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }
}