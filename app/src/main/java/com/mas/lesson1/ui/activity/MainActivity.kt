package com.mas.lesson1.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mas.lesson1.R
import com.mas.lesson1.databinding.ActivityMainBinding
import com.mas.lesson1.mvp.presenter.MainPresenter
import com.mas.lesson1.mvp.view.MainView
import com.mas.lesson1.ui.App
import com.mas.lesson1.ui.BackClickListener
import com.mas.lesson1.ui.adapter.UsersRVAdapter
import com.mas.lesson1.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackClickListener && it.backPressed()) {
                return
            }
            presenter.backClick()
        }
    }

}