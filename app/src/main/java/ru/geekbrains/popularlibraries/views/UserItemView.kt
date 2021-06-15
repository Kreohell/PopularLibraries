package ru.geekbrains.popularlibraries.views

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url:String)
}