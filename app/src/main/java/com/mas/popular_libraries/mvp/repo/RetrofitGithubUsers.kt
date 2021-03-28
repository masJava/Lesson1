package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsers(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsers {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUser = users.map { user ->
                        RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                    }
                    db.userDao.insert(roomUser)
                    users
                }

            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
            }
        }

    }.subscribeOn(Schedulers.io())
}