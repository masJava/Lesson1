package com.mas.lesson1.ui.activity

import android.os.Bundle
import com.mas.lesson1.databinding.ActivityMainBinding
import com.mas.lesson1.mvp.model.ModelCounters
import com.mas.lesson1.mvp.presenter.PresenterCounter
import com.mas.lesson1.mvp.view.ViewCounter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), ViewCounter {
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        PresenterCounter(ModelCounters())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btn0?.setOnClickListener {
            presenter.counter0Click()
        }
        vb?.btn1?.setOnClickListener {
            presenter.counter1Click()
        }
        vb?.btn2?.setOnClickListener {
            presenter.counter2Click()
        }
    }

    override fun setButton0Text(text: String) {
        vb?.btn0?.text = text
    }

    override fun setButton1Text(text: String) {
        vb?.btn1?.text = text
    }

    override fun setButton2Text(text: String) {
        vb?.btn2?.text = text
    }
}