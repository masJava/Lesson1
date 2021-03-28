package com.mas.popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mas.popular_libraries.databinding.FragmentRepoInfoBinding
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepository
import com.mas.popular_libraries.mvp.presenter.RepoInfoPresenter
import com.mas.popular_libraries.mvp.view.RepoInfoView
import com.mas.popular_libraries.ui.App
import com.mas.popular_libraries.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    companion object {
        private const val REPO_ARG = "repo"
        fun newInstance(repo: GithubUserRepository) = RepoInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_ARG, repo)
            }
        }
    }

    private val presenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubUserRepository>(REPO_ARG) as GithubUserRepository
        RepoInfoPresenter(
            repo,
            App.instance.router
        )
    }

    private var vb: FragmentRepoInfoBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRepoInfoBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun setRepoInfo(repo: GithubUserRepository) {
        vb?.tvRepoName?.text = repo.name
        vb?.tvDescription?.text = "Description\n${repo.description}"
        vb?.tvHtmlUrl?.text = repo.htmlUrl
        vb?.tvForkCount?.text = "Fork count: ${repo.forks_count}"
    }

}