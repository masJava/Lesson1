package com.mas.lesson1.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mas.lesson1.databinding.ActivityMainBinding
import com.mas.lesson1.mvp.model.GithubUsersRepo
import com.mas.lesson1.mvp.presenter.MainPresenter
import com.mas.lesson1.mvp.view.MainView
import com.mas.lesson1.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(GithubUsersRepo())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(this)
        vb?.rvUsers?.adapter = UsersRVAdapter(presenter.usersListPresenter)
    }

    override fun updateList() {
        vb?.rvUsers?.adapter?.notifyDataSetChanged()
    }


}