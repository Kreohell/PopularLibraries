package ru.geekbrains.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popularlibraries.fragments.UserFragment
import ru.geekbrains.popularlibraries.fragments.UsersFragment
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.presenter.IScreens

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}