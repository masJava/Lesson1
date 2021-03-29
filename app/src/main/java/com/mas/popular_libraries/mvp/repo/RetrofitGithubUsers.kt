package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(val api: IDataSource) : IGithubUsers {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}