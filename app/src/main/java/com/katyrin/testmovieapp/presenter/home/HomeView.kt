package com.katyrin.testmovieapp.presenter.home

import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.presenter.ErrorView
import com.katyrin.testmovieapp.presenter.LoadingView
import com.katyrin.testmovieapp.presenter.main.MainView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView : MainView, ErrorView, LoadingView, MvpView {
    fun showRecyclerView(data: List<RecyclerData>, genre: String? = null)
    fun showEmptyList()
}