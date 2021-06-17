package ru.geekbrains.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.App
import ru.geekbrains.popularlibraries.adapters.RepositoriesRVAdapter
import ru.geekbrains.popularlibraries.api.ApiHolder
import ru.geekbrains.popularlibraries.cache.GithubReposCacheImpl
import ru.geekbrains.popularlibraries.databinding.FragmentUserBinding
import ru.geekbrains.popularlibraries.model.GithubUser
import ru.geekbrains.popularlibraries.model.RetrofitGithubRepositoriesRepo
import ru.geekbrains.popularlibraries.network.AndroidNetworkStatus
import ru.geekbrains.popularlibraries.presenter.BackButtonListener
import ru.geekbrains.popularlibraries.presenter.UserPresenter
import ru.geekbrains.popularlibraries.room.Database
import ru.geekbrains.popularlibraries.views.UserView

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null
    private var adapter: RepositoriesRVAdapter? = null
    private val database: Database by lazy {
        Database.apply { create(requireContext()) }.getInstance()
    }
    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(
            App.instance.router,
            user,
            RetrofitGithubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                GithubReposCacheImpl(database)
            ),
            AndroidSchedulers.mainThread()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun setLogin(text: String) {
        vb?.userLoginText?.text = text
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.reposListPresenter)
        vb?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        vb = null
        adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val USER = "USER"
        fun newInstance(user: GithubUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, user)
                }
            }
    }
}