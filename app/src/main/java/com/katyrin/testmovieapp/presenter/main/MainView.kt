package com.katyrin.testmovieapp.presenter.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun init()
}