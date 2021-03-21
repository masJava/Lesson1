package com.mas.lesson1.mvp.repo

import com.mas.lesson1.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsersRepos(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())
}