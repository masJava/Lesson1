package com.mas.popular_libraries.di

import com.mas.popular_libraries.di.module.*
import com.mas.popular_libraries.mvp.presenter.MainPresenter
import com.mas.popular_libraries.mvp.presenter.RepoInfoPresenter
import com.mas.popular_libraries.mvp.presenter.UsersInfoPresenter
import com.mas.popular_libraries.mvp.presenter.UsersPresenter
import com.mas.popular_libraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersInfoPresenter: UsersInfoPresenter)
    fun inject(repoInfoPresenter: RepoInfoPresenter)
}