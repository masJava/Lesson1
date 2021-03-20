package com.mas.lesson1.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.lesson1.mvp.model.entity.GithubUser
import com.mas.lesson1.mvp.navigation.IScreens
import com.mas.lesson1.mvp.presenter.list.IUsersListPresenter
import com.mas.lesson1.mvp.repo.IGithubUsersRepo
import com.mas.lesson1.mvp.view.UsersView
import com.mas.lesson1.mvp.view.list.IUserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter


class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.loadAvatar(user.avatarUrl)
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

        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe(
                { users ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                },
                { t -> Log.d("my", t.message.toString()) })

    }

    fun backClick(): Boolean {
        Log.d("my", "usersPres")
        router.exit()
        return true
    }

}