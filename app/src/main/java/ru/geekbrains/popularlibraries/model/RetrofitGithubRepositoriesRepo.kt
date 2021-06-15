package ru.geekbrains.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibraries.api.IDataSource

class RetrofitGithubRepositoriesRepo(val api: IDataSource): IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GitHubRepo>> =
        user.reposUrl?.let { url -> api.getRepo(url).subscribeOn(Schedulers.io())
        } ?: Single
            .error<List<GitHubRepo>>(RuntimeException("Failed repos list"))
            .subscribeOn(Schedulers.io())
}