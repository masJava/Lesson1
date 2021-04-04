package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUser

interface IGithubUsersCache {
    fun insert(users: List<GithubUser>)
    fun getAll(): List<GithubUser>
}