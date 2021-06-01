package ru.geekbrains.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.geekbrains.popularlibraries.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(CountersModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding
            .inflate(layoutInflater)
            .also { vb -> setContentView(vb.root) }
            .apply {
                btnCounter1.setOnClickListener { presenter.counter1Click() }
                btnCounter2.setOnClickListener { presenter.counter2Click() }
                btnCounter3.setOnClickListener { presenter.counter3Click() }
            }
    }

    override fun setCounter1Value(value: String) {
        vb?.btnCounter1?.text = value
    }

    override fun setCounter2Text(value: String) {
        vb?.btnCounter2?.text = value
    }

    override fun setCounter3Text(value: String) {
        vb?.btnCounter3?.text = value
    }
}



