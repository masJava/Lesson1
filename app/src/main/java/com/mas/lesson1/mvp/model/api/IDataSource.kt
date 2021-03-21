package com.mas.lesson1.mvp.model.api

import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>


//    @GET("users")
//    fun getUsers(@Header("Authorization") token: String) : Single<List<GithubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubUserRepos>>

}