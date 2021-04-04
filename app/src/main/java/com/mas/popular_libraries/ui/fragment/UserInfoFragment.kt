package com.mas.popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.popular_libraries.databinding.FragmentUserInfoBinding
import com.mas.popular_libraries.mvp.model.api.ApiHolder
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.room.cache.RoomGithubRepositoryCache
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.presenter.UsersInfoPresenter
import com.mas.popular_libraries.mvp.repo.RetrofitGithubUsersRepository
import com.mas.popular_libraries.mvp.view.UsersInfoView
import com.mas.popular_libraries.ui.App
import com.mas.popular_libraries.ui.BackButtonListener
import com.mas.popular_libraries.ui.adapter.ReposRVAdapter
import com.mas.popular_libraries.ui.image.GlideImageLoader
import com.mas.popular_libraries.ui.network.AndroidNetworkStatus
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
            RetrofitGithubUsersRepository(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                Database.getInstance(),
                RoomGithubRepositoryCache()
            ),
            AndroidSchedulers.mainThread()
        ).apply {
            App.instance.appComponent.inject(this)
        }
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