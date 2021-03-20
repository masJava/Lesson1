package com.mas.lesson1.mvp.model.api

import com.mas.lesson1.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>


//    @GET("users")
//    fun getUsers(@Header("Authorization") token: String) : Single<List<GithubUser>>

//    @GET
//    fun getUserRepos(@Url url: String) : Single

}