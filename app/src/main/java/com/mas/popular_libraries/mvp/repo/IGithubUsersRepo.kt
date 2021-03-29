package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsersRepos(url: String): Single<List<GithubUserRepos>>
}