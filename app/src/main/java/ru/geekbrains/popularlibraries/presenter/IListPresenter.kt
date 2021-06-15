package ru.geekbrains.popularlibraries.presenter

import ru.geekbrains.popularlibraries.views.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}