package com.katyrin.testmovieapp.presenter.home

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.model.interactor.MainInteractor
import com.katyrin.testmovieapp.utils.CodeThrottle
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: Router,
    private val mainInteractor: MainInteractor
) : MvpPresenter<HomeView>() {

    private var codeThrottle: CodeThrottle? = CodeThrottle()
    private val presenterCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun handleError(error: Throwable) {
        viewState.showEmptyList()
        viewState.showError(error.message)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        getFilms()
    }

    fun navigateToScreen(screen: FragmentScreen) {
        codeThrottle?.throttle { router.navigateTo(screen) }
    }

    fun getFilmsByGenre(genre: String? = null) {
        cancelJob()
        presenterCoroutineScope.launch {
            val recyclerData: List<RecyclerData> = mainInteractor.getFilmsByGenre(genre)
            viewState.showRecyclerView(recyclerData, genre)
            viewState.showNormalState()
        }
    }

    fun getFilms() {
        viewState.showLoadingState()
        cancelJob()
        presenterCoroutineScope.launch {
            val recyclerData: List<RecyclerData> = mainInteractor.getFilms()
            viewState.showRecyclerView(recyclerData)
            viewState.showNormalState()
        }
    }

    override fun onDestroy() {
        cancelJob()
        codeThrottle = null
        super.onDestroy()
    }

    private fun cancelJob() {
        presenterCoroutineScope.coroutineContext.cancelChildren()
    }
}