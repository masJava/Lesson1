package com.mas.lesson1.mvp.repo

import com.mas.lesson1.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(val api: IDataSource) : IGithubUsers {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}