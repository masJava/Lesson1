package com.mas.lesson1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.lesson1.databinding.FragmentUsersBinding
import com.mas.lesson1.mvp.model.api.ApiHolder
import com.mas.lesson1.mvp.presenter.UsersPresenter
import com.mas.lesson1.mvp.repo.RetrofitGithubUsersRepo
import com.mas.lesson1.mvp.view.UsersView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackButtonListener
import com.mas.lesson1.ui.adapter.UsersRVAdapter
import com.mas.lesson1.ui.image.GlideImageLoader
import com.mas.lesson1.ui.navigation.AndroidScreens
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
            RetrofitGithubUsersRepo(ApiHolder.api),
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