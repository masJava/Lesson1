package com.mas.popular_libraries.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mas.popular_libraries.mvp.model.entity.GithubUser
import com.mas.popular_libraries.mvp.model.entity.GithubUserRepos
import com.mas.popular_libraries.mvp.navigation.IScreens
import com.mas.popular_libraries.mvp.presenter.list.IRepoListPresenter
import com.mas.popular_libraries.mvp.repo.IGithubUsersRepo
import com.mas.popular_libraries.mvp.view.UsersInfoView
import com.mas.popular_libraries.mvp.view.list.IRepoItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersInfoPresenter(
    private val user: GithubUser,
    private val repos: IGithubUsersRepo,
    private val uiScheduler: Scheduler,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersInfoView>() {

    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GithubUserRepos>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
        }

        override fun getCount() = repos.size
    }

    val reposListPresenter = ReposListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
        viewState.setAvatar(user.avatarUrl)
        viewState.init()
        loadData(user.reposUrl)

        reposListPresenter.itemClickListener = { view ->
            val repo = reposListPresenter.repos[view.pos]
            Log.d(
                "my", "name: ${repo.name} " +
                        "\ndescription:${repo.description} " +
                        "\nforks_count:${repo.forks_count}"
            )
            router.navigateTo(screens.repoInfo(repo))
        }
    }

    private fun loadData(url: String) {

        repos.getUsersRepos(url)
            .observeOn(uiScheduler)
            .subscribe(
                { repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                },
                { t -> Log.d("my", t.message.toString()) })

    }


    fun backClick(): Boolean {
        Log.d("my", "UserInfoPres")
        router.exit()
        return true
    }
}