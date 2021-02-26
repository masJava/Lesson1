package com.mas.lesson1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mas.lesson1.databinding.ActivityMainBinding
import com.mas.lesson1.mvp.presenter.PresenterCounter
import com.mas.lesson1.mvp.view.ViewCounter

class MainActivity : AppCompatActivity(), ViewCounter {
    private var vb: ActivityMainBinding? = null
    private val presenter = PresenterCounter(this)

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

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> vb?.btn0?.text = text
            1 -> vb?.btn1?.text = text
            2 -> vb?.btn2?.text = text
        }
    }
}