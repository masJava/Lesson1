package com.mas.lesson1.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepos(
    @Expose val name: String,
    @Expose val description: String,
    @Expose val htmlUrl: String,
    @Expose val forks_count: Int
) : Parcelable
