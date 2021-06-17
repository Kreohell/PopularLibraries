package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.AndroidScreens
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.model.IGithubRepositoriesRepo
import ru.geekbrains.popularlibraries.views.UserView

class UserPresenter(
    private val router: Router,
    private val user: GithubUser,
    private val repositoriesRepo: IGithubRepositoriesRepo,
    private val uiScheduler: Scheduler
) : MvpPresenter<UserView>() {

    class RepoListPresenter: IRepoListPresenter {

        val repos = mutableListOf<GitHubRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setName(it) }
        }
    }

    val reposListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        viewState.init()
        user.login?.let { viewState.setLogin(it) }
        loadData()

        reposListPresenter.itemClickListener = { itemView ->
            val repo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(AndroidScreens.RepositoryScreen(repo).getFragment())
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repos -> updateRepos(repos) }, { it.printStackTrace() })
    }

    fun updateRepos(reposList: List<GitHubRepo>) {
        reposListPresenter.repos.clear()
        reposListPresenter.repos.addAll(reposList)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}