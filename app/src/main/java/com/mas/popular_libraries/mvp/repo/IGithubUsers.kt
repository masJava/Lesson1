package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsers {
    fun getUsers(): Single<List<GithubUser>>
}