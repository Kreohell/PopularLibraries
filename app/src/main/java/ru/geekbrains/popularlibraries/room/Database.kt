package ru.geekbrains.popularlibraries.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1
)

abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}