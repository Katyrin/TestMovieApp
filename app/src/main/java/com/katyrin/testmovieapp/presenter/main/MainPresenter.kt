package com.katyrin.testmovieapp.presenter.main

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.katyrin.testmovieapp.view.home.HomeScreen
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        replaceScreen(HomeScreen)
        viewState.init()
    }

    private fun replaceScreen(screen: FragmentScreen) {
        router.newRootScreen(screen)
    }
}