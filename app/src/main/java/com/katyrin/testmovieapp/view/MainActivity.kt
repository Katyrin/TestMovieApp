package com.katyrin.testmovieapp.view

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.katyrin.testmovieapp.R
import com.katyrin.testmovieapp.databinding.ActivityMainBinding
import com.katyrin.testmovieapp.presenter.main.MainPresenter
import com.katyrin.testmovieapp.presenter.main.MainView
import com.katyrin.testmovieapp.view.abs.AbsActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : AbsActivity(R.layout.activity_main), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(activity = this, R.id.container)
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun init() {}

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}