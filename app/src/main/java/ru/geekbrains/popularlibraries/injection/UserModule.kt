package ru.geekbrains.popularlibraries.injection

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.cache.GithubReposCacheImpl
import ru.geekbrains.popularlibraries.cache.GithubRepositoriesCache
import ru.geekbrains.popularlibraries.cache.GithubUsersCache
import ru.geekbrains.popularlibraries.cache.GithubUsersCacheImpl
import ru.geekbrains.popularlibraries.model.IGithubRepositoriesRepo
import ru.geekbrains.popularlibraries.model.IGithubUsersRepo
import ru.geekbrains.popularlibraries.model.RetrofitGithubRepositoriesRepo
import ru.geekbrains.popularlibraries.model.RetrofitGithubUsersRepo
import ru.geekbrains.popularlibraries.room.Database
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
interface UserModule {
    @Singleton
    @Binds
    fun usersRepo(retrofitGithubUsersRepo: RetrofitGithubUsersRepo): IGithubUsersRepo

    @Singleton
    @Binds
    fun usersCache(githubUsersCacheImpl: GithubUsersCacheImpl): GithubUsersCache

    @Singleton
    @Binds
    fun githubReposCache(githubReposCacheImpl: GithubReposCacheImpl): GithubRepositoriesCache

    @Singleton
    @Binds
    fun iGitReposRepo(retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo): IGithubRepositoriesRepo

    companion object {
        @Singleton
        @Provides
        fun database(context: Context): Database =
            Room.databaseBuilder(context, Database::class.java, Database.DB_NAME).build()
    }
}