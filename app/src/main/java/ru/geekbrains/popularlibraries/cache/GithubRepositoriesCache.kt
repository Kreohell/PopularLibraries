package ru.geekbrains.popularlibraries.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.model.GithubUser

interface GithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GitHubRepo>>
    fun putUserRepos(user: GithubUser, repos: List<GitHubRepo>): Completable
}