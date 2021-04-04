package com.mas.popular_libraries.di.module

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubRepositoryCache
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import com.mas.popular_libraries.mvp.repo.IGithubUsersRepo
import com.mas.popular_libraries.mvp.repo.RetrofitGithubUsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserRepositoryModule {

    @Singleton
    @Provides
    fun repos(
        api: IDataSource,
        networkStatus: INetworkStatus,
        repositoryCache: IGithubRepositoryCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepository(api, networkStatus, repositoryCache)
}