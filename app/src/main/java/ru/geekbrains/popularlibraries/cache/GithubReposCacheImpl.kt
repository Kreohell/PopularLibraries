package ru.geekbrains.popularlibraries.cache


import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.room.Database
import ru.geekbrains.popularlibraries.room.RoomGithubRepo
import java.lang.RuntimeException
import javax.inject.Inject

class GithubReposCacheImpl @Inject constructor(private val db: Database): GithubRepositoriesCache {

    override fun getUserRepos(user: GithubUser): Single<List<GitHubRepo>> =
        Single.fromCallable {
            val roomUsers = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No user in cache")

            return@fromCallable db.repositoryDao.findForUser(roomUsers.id).map {
                GitHubRepo(it.id, it.name, it.forksCount)
            }
        }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repos: List<GitHubRepo>): Completable =
        Completable.fromAction {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No user in cache")
            val roomRepos = repos.map {
                RoomGithubRepo(it.id ?: "", it.name ?: "", it.forksCount ?: 0, roomUser.id)
            }
            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())
}