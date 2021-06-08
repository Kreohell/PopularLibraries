package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.AndroidScreens
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.model.GithubUsersRepo
import ru.geekbrains.popularlibraries.views.UserItemView
import ru.geekbrains.popularlibraries.views.UsersView

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val uiSched: Scheduler
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens.user(user))
        }
    }

    fun loadData() {
        disposable.add(usersRepo.getUsers()
            .observeOn(uiSched)
            .subscribe( { users -> subscribeUsers(users) }, { it.printStackTrace() }))
    }

    fun subscribeUsers(users: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}