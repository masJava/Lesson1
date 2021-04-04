package com.mas.popular_libraries.di.module

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubUsersCache
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import com.mas.popular_libraries.mvp.repo.IGithubUsers
import com.mas.popular_libraries.mvp.repo.RetrofitGithubUsers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun users(
        api: IDataSource,
        networkStatus: INetworkStatus,
        usersCache: IGithubUsersCache
    ): IGithubUsers = RetrofitGithubUsers(api, networkStatus, usersCache)
}