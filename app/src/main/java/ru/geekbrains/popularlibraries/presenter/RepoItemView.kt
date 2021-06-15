package ru.geekbrains.popularlibraries.presenter

import ru.geekbrains.popularlibraries.views.IItemView

interface RepoItemView: IItemView {
    fun setName(name: String)
}