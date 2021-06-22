package ru.geekbrains.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibraries.api.IDataSource
import ru.geekbrains.popularlibraries.cache.GithubRepositoriesCache
import ru.geekbrains.popularlibraries.network.INetworkStatus
import java.lang.RuntimeException
import javax.inject.Inject

class RetrofitGithubRepositoriesRepo @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val githubReposCache: GithubRepositoriesCache
): IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GitHubRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepo(url)
                        .flatMap { repos ->
                            githubReposCache.putUserRepos(user, repos).toSingleDefault(repos)
                        }
                } ?: Single.error<List<GitHubRepo>>(RuntimeException("No user in cache"))
                    .subscribeOn(Schedulers.io())
            } else {
                githubReposCache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}