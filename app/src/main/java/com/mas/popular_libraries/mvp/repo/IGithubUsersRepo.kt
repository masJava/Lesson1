package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsersRepository(user: GithubUser): Single<List<GithubUserRepository>>
}