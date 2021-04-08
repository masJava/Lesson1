package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubUsersCache
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database,
    val usersCache: IGithubUsersCache
) : IGithubUsers {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    usersCache.insert(db, users)
                    users
                }

            }
        } else {
            Single.fromCallable {
                usersCache.getAll(db)
            }
        }

    }.subscribeOn(Schedulers.io())
}