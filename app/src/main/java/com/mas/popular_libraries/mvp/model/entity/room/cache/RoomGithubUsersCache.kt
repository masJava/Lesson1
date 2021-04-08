package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.mas.popular_libraries.mvp.model.entity.room.db.Database

class RoomGithubUsersCache : IGithubUsersCache {
    override fun insert(db: Database, users: List<GithubUser>) {
        val roomUser = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
        }
        db.userDao.insert(roomUser)
    }

    override fun getAll(db: Database): List<GithubUser> {
        return db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }
}