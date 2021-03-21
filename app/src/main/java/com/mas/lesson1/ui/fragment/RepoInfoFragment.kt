package com.mas.lesson1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mas.lesson1.databinding.FragmentRepoInfoBinding
import com.mas.lesson1.mvp.model.entity.GithubUserRepos
import com.mas.lesson1.mvp.presenter.RepoInfoPresenter
import com.mas.lesson1.mvp.view.RepoInfoView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    companion object {
        private const val REPO_ARG = "repo"
        fun newInstance(repo: GithubUserRepos) = RepoInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_ARG, repo)
            }
        }
    }

    private val presenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubUserRepos>(REPO_ARG) as GithubUserRepos
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

    override fun setRepoInfo(repo: GithubUserRepos) {
        vb?.tvRepoName?.text = repo.name
        vb?.tvDescription?.text = "Description\n${repo.description}"
        vb?.tvHtmlUrl?.text = repo.htmlUrl
        vb?.tvForkCount?.text = "Fork count: ${repo.forks_count}"
    }

}