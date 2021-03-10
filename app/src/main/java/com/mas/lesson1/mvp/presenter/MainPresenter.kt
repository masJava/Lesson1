package com.mas.lesson1.mvp.presenter

import com.mas.lesson1.mvp.model.GithubUsersRepo
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.presenter.list.IUsersListPresenter
import com.mas.lesson1.mvp.view.MainView
import com.mas.lesson1.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(usersRepo.getUsers())
        viewState.updateList()
    }

}