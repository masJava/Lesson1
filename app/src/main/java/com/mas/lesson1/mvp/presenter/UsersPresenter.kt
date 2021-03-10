package com.mas.lesson1.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.model.GithubUsersRepo
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.mvp.presenter.list.IUsersListPresenter
import com.mas.lesson1.mvp.view.UsersView
import com.mas.lesson1.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

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

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            Log.d("my", user.login)
            router.navigateTo(screens.userInfo(user))
        }
    }

    private fun loadData() {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(usersRepo.getUsers())
        viewState.updateList()
    }

    fun backClick(): Boolean {
        router.exit()
        Log.d("my", "backUser")
        return true
    }

}