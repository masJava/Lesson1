package com.mas.lesson1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.lesson1.databinding.FragmentUserInfoBinding
import com.mas.lesson1.mvp.model.api.ApiHolder
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.presenter.UsersInfoPresenter
import com.mas.lesson1.mvp.repo.RetrofitGithubUsersRepo
import com.mas.lesson1.mvp.view.UsersInfoView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackButtonListener
import com.mas.lesson1.ui.adapter.ReposRVAdapter
import com.mas.lesson1.ui.image.GlideImageLoader
import com.mas.lesson1.ui.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UsersInfoView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UsersInfoPresenter(
            user,
            RetrofitGithubUsersRepo(ApiHolder.api),
            AndroidSchedulers.mainThread(),
            App.instance.router,
            AndroidScreens()
        )
    }

    private var vb: FragmentUserInfoBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvRepos?.adapter = ReposRVAdapter(presenter.reposListPresenter)
    }

    override fun updateList() {
        vb?.rvRepos?.adapter?.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        vb?.tvUserLogin?.text = text
    }

    override fun setAvatar(urlAvatar: String) {
        vb?.ivUserAvatar?.let {
            GlideImageLoader().load(urlAvatar, it)
        }
    }

    override fun backPressed() = presenter.backClick()

}