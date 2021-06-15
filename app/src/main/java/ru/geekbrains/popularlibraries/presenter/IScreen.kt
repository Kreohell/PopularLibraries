package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Screen

interface IScreen {
    fun getFragment(): Screen
}