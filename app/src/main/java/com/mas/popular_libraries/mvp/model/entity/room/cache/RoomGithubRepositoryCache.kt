package com.mas.popular_libraries.mvp.model.entity.room.cache

import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubRepository
import com.mas.popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.mas.popular_libraries.mvp.model.entity.room.db.Database

class RoomGithubRepositoryCache : IGithubRepositoryCache {
    override fun insert(
        db: Database,
        repositories: List<GithubUserRepository>,
        roomUser: RoomGithubUser
    ) {
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
    }

    override fun getByUser(db: Database, user: RoomGithubUser): List<GithubUserRepository> {
        return db.repositoryDao.findByUser(user.id).map { roomRepo ->
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