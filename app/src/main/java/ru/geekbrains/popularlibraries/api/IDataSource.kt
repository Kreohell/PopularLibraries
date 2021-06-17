package ru.geekbrains.popularlibraries.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.model.GithubUser

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepo(@Url url: String): Single<List<GitHubRepo>>
}