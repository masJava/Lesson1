package com.mas.popular_libraries.di.module

import androidx.room.Room
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubRepositoryCache
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubUsersCache
import com.mas.popular_libraries.mvp.model.entity.room.cache.RoomGithubRepositoryCache
import com.mas.popular_libraries.mvp.model.entity.room.cache.RoomGithubUsersCache
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun dataBase(app: App) =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

    @Singleton
    @Provides
    fun repositoryCache(db: Database): IGithubRepositoryCache = RoomGithubRepositoryCache(db)
}