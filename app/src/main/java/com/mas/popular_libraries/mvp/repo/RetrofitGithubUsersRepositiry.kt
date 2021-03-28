package com.mas.popular_libraries.mvp.repo

import com.mas.popular_libraries.mvp.model.api.IDataSource
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubRepository
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepositiry(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database
) : IGithubUsersRepo {
    override fun getUsersRepository(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getUserRepos(url).flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = db.userDao.findByLogin(user.login)
                                ?: throw RuntimeException("Нет пользователя в БД")
                            val roomRepos = repositories.map { repo ->
                                RoomGithubRepository(
                                    repo.id,
                                    repo.name,
                                    repo.description,
                                    repo.htmlUrl,
                                    repo.forks_count,
                                    roomUser.id
                                )
                            }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login)
                        ?: throw RuntimeException("Нет пользователя в БД")
                    db.repositoryDao.findByUser(roomUser.id).map { roomRepo ->
                        GithubUserRepository(
                            roomRepo.id,
                            roomRepo.name,
                            roomRepo.description,
                            roomRepo.htmlUrl,
                            roomRepo.forks_count
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}