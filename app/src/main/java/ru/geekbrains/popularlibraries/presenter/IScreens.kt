package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.popularlibraries.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}