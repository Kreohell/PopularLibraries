package ru.geekbrains.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibraries.api.IDataSource
import ru.geekbrains.popularlibraries.cache.GithubUsersCache
import ru.geekbrains.popularlibraries.network.INetworkStatus

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val githubUsersCache: GithubUsersCache
): IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    api.getUsers()
                        .flatMap { users ->
                            githubUsersCache.putUsers(users).toSingleDefault(users)
                        }
                } else {
                    githubUsersCache.getUsers()
                }
            }.subscribeOn(Schedulers.io())
}