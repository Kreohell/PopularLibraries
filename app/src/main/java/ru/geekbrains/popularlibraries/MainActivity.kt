package ru.geekbrains.popularlibraries


import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.databinding.ActivityMainBinding
import ru.geekbrains.popularlibraries.presenter.BackButtonListener
import ru.geekbrains.popularlibraries.presenter.MainPresenter
import javax.inject.Inject


class MainActivity :MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = AppNavigator(this, R.id.container)
    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter().apply { App.instance.appComponent.inject(this) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}




