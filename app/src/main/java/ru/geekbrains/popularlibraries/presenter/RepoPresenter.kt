package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.views.RepoView
import javax.inject.Inject

class RepoPresenter(private val gitHubRepo: GitHubRepo): MvpPresenter<RepoView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(gitHubRepo.id ?: "")
        viewState.setTitle(gitHubRepo.name ?: "")
        viewState.setForksCount((gitHubRepo.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}