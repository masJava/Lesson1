package com.mas.lesson1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.lesson1.databinding.FragmentUsersBinding
import com.mas.lesson1.mvp.model.GithubUsersRepo
import com.mas.lesson1.mvp.presenter.UsersPresenter
import com.mas.lesson1.mvp.view.UsersView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackClickListener
import com.mas.lesson1.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
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
        vb?.rvUsers?.adapter = UsersRVAdapter(presenter.usersListPresenter)
    }

    override fun updateList() {
        vb?.rvUsers?.adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}