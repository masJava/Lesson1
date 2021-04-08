package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.room.cache.IGithubRepositoryCache
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepository(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database,
    val repositoryCache: IGithubRepositoryCache
) : IGithubUsersRepo {
    override fun getUsersRepository(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getUserRepos(url).flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = db.userDao.findByLogin(user.login)
                                ?: throw RuntimeException("Нет пользователя в БД")
                            repositoryCache.insert(db, repositories, roomUser)
                            repositories
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login)
                        ?: throw RuntimeException("Нет пользователя в БД")
                    repositoryCache.getByUser(db, roomUser)
                }
            }
        }.subscribeOn(Schedulers.io())
}