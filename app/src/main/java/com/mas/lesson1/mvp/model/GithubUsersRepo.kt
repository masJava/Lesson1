package com.mas.lesson1.mvp.model

import com.mas.lesson1.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("User1"),
        GithubUser("User2"),
        GithubUser("User3"),
        GithubUser("User4"),
        GithubUser("User5"),
        GithubUser("User6"),
        GithubUser("User7"),
        GithubUser("User8"),
        GithubUser("User9"),
        GithubUser("User10"),
        GithubUser("User11"),
        GithubUser("User12"),
        GithubUser("User13"),
        GithubUser("User14"),
    )

    fun getUsers(): Observable<List<GithubUser>> = Observable.just(users)
}