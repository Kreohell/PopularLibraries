package ru.geekbrains.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.App
import ru.geekbrains.popularlibraries.databinding.FragmentRepositoryBinding
import ru.geekbrains.popularlibraries.model.GitHubRepo
import ru.geekbrains.popularlibraries.presenter.BackButtonListener
import ru.geekbrains.popularlibraries.presenter.RepoPresenter
import ru.geekbrains.popularlibraries.views.RepoView

class RepositoryFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {

    private var vb: FragmentRepositoryBinding? = null
    val presenter: RepoPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GitHubRepo>(REPO) as GitHubRepo
        RepoPresenter(repo).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    companion object {
        private const val REPO = "repository"
        @JvmStatic
        fun newInstance(repo: GitHubRepo) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO, repo)
            }
        }
    }

    override fun init() {}

    override fun setId(id: String) {
        val idText = "id: $id"
        vb?.idTv?.text = idText
    }

    override fun setTitle(title: String) {
        val nameText = "Name: $title"
        vb?.titleTv?.text = nameText
    }

    override fun setForksCount(forksCount: String) {
        val forksText = "Forks Count: $forksCount"
        vb?.countForksTv?.text = forksText
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}