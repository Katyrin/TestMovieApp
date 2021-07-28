package com.katyrin.testmovieapp.presenter.content

import moxy.MvpPresenter
import javax.inject.Inject

class ContentPresenter @Inject constructor() : MvpPresenter<ContentView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }
}