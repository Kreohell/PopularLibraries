package ru.geekbrains.popularlibraries


class MainPresenter(private val model: CountersModel, private val view: MainView) {

    fun counter1Click() {
        model.next(0)
            .let { counter -> view.setCounter1Value("$counter") }
    }

    fun counter2Click() {
        model.next(1)
            .let { counter -> view.setCounter2Text("$counter") }
    }

    fun counter3Click() {
        model.next(2)
            .let { counter -> view.setCounter3Text("$counter") }
    }
}

