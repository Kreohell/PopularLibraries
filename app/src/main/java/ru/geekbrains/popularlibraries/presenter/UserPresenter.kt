package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.views.UserView

class UserPresenter(private val router: Router, private val user: GithubUser) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}