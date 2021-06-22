package ru.geekbrains.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.AndroidScreens
import ru.geekbrains.popularlibraries.App
import ru.geekbrains.popularlibraries.adapters.UsersRVAdapter
import ru.geekbrains.popularlibraries.api.ApiHolder
import ru.geekbrains.popularlibraries.api.GlideImageLoader
import ru.geekbrains.popularlibraries.cache.GithubUsersCacheImpl
import ru.geekbrains.popularlibraries.databinding.FragmentUsersBinding
import ru.geekbrains.popularlibraries.model.GithubUsersRepo
import ru.geekbrains.popularlibraries.model.RetrofitGithubUsersRepo
import ru.geekbrains.popularlibraries.network.AndroidNetworkStatus
import ru.geekbrains.popularlibraries.presenter.BackButtonListener
import ru.geekbrains.popularlibraries.presenter.UsersPresenter
import ru.geekbrains.popularlibraries.room.Database
import ru.geekbrains.popularlibraries.views.UsersView

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }
    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}