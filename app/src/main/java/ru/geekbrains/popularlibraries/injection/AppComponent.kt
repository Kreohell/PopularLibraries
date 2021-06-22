package ru.geekbrains.popularlibraries.injection

import dagger.Component
import ru.geekbrains.popularlibraries.MainActivity
import ru.geekbrains.popularlibraries.presenter.MainPresenter
import ru.geekbrains.popularlibraries.presenter.RepoPresenter
import ru.geekbrains.popularlibraries.presenter.UserPresenter
import ru.geekbrains.popularlibraries.presenter.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkStateModule::class, UserModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepoPresenter)
}