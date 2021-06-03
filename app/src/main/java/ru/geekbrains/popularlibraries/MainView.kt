package ru.geekbrains.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setCounter1Value(value: String)
    fun setCounter2Text(value: String)
    fun setCounter3Text(value: String)
}