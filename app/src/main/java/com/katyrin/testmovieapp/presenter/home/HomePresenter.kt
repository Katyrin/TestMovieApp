package com.katyrin.testmovieapp.presenter.home

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<HomeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showLoadingState()
        getMovies()
    }

    private fun getMovies() {

    }
}