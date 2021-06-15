package ru.geekbrains.popularlibraries.api

interface IImageLoader<T> {
    fun loadIntro(url: String, container: T)
}