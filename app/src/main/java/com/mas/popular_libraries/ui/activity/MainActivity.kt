package com.mas.popular_libraries.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mas.popular_libraries.R
import com.mas.popular_libraries.databinding.ActivityMainBinding
import com.mas.popular_libraries.mvp.presenter.MainPresenter
import com.mas.popular_libraries.mvp.view.MainView
import com.mas.popular_libraries.ui.App
import com.mas.popular_libraries.ui.BackButtonListener
import com.mas.popular_libraries.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

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
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }

}