package ru.geekbrains.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibraries.api.IDataSource

class RetrofitGithubUsersRepo(val api: IDataSource): IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        api.getUsers().subscribeOn(Schedulers.io()) ?: Single
            .error<List<GithubUser>>(RuntimeException("Users not load"))
            .subscribeOn(Schedulers.io())
}