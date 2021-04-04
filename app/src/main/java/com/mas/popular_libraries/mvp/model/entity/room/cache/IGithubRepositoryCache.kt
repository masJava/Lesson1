package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository

interface IGithubRepositoryCache {
    fun insert(repositories: List<GithubUserRepository>, user: GithubUser)
    fun getByUser(user: GithubUser): List<GithubUserRepository>
}