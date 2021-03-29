package com.mas.popular_libraries.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos
import com.mas.popular_libraries.mvp.navigation.IScreens
import com.mas.popular_libraries.ui.fragment.RepoInfoFragment
import com.mas.popular_libraries.ui.fragment.UserInfoFragment
import com.mas.popular_libraries.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun userInfo(user: GithubUser) = FragmentScreen {
        UserInfoFragment.newInstance(user)
    }

    override fun repoInfo(repo: GithubUserRepos) = FragmentScreen {
        RepoInfoFragment.newInstance(repo)
    }
}