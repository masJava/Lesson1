package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.mas.popular_libraries.mvp.model.entity.room.db.Database

interface IGithubRepositoryCache {
    fun insert(db: Database, repositories: List<GithubUserRepository>, roomUser: RoomGithubUser)
    fun getByUser(db: Database, user: RoomGithubUser): List<GithubUserRepository>
}