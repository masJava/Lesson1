package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.room.db.Database

interface IGithubUsersCache {
    fun insert(db: Database, users: List<GithubUser>)
    fun getAll(db: Database): List<GithubUser>
}