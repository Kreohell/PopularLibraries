package ru.geekbrains.popularlibraries

import moxy.MvpPresenter


class MainPresenter(private val model: CountersModel): MvpPresenter<MainView>() {

    fun counter1Click() {
        model.next(0)
            .let { counter -> viewState.setCounter1Value("$counter") }
    }

    fun counter2Click() {
        model.next(1)
            .let { counter -> viewState.setCounter2Text("$counter") }
    }

    fun counter3Click() {
        model.next(2)
            .let { counter -> viewState.setCounter3Text("$counter") }
    }
}

