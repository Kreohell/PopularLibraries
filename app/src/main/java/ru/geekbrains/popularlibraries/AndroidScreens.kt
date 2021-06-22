package ru.geekbrains.popularlibraries

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popularlibraries.fragments.RepositoryFragment
import ru.geekbrains.popularlibraries.fragments.UserFragment
import ru.geekbrains.popularlibraries.fragments.UsersFragment
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.presenter.IScreen
import ru.geekbrains.popularlibraries.presenter.IScreens

object AndroidScreens {

    class UsersScreen: IScreen {
        override fun getFragment(): Screen = FragmentScreen { UsersFragment.newInstance() }

    }

    class UserScreen(private val user: GithubUser): IScreen {
        override fun getFragment(): Screen = FragmentScreen { UserFragment.newInstance(user) }
    }

    class RepositoryScreen(private val repo: GitHubRepo) : IScreen {
        override fun getFragment(): Screen = FragmentScreen { RepositoryFragment.newInstance(repo) }
    }
}