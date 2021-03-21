package com.mas.lesson1.mvp.repo

import com.mas.lesson1.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsersRepos(url: String): Single<List<GithubUserRepos>>
}