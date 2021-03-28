package com.mas.popular_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.popular_libraries.databinding.FragmentUsersBinding
import com.mas.popular_libraries.mvp.model.api.ApiHolder
import com.mas.popular_libraries.mvp.model.entity.room.db.Database
import com.mas.popular_libraries.mvp.presenter.UsersPresenter
import com.mas.popular_libraries.mvp.repo.RetrofitGithubUsers
import com.mas.popular_libraries.mvp.view.UsersView
import com.mas.popular_libraries.ui.App
import com.mas.popular_libraries.ui.BackButtonListener
import com.mas.popular_libraries.ui.adapter.UsersRVAdapter
import com.mas.popular_libraries.ui.image.GlideImageLoader
import com.mas.popular_libraries.ui.navigation.AndroidScreens
import com.mas.popular_libraries.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsers(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                Database.getInstance()
            ),
            App.instance.router,
            AndroidScreens()
        )
    }

    private var vb: FragmentUsersBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
    }

    override fun updateList() {
        vb?.rvUsers?.adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}