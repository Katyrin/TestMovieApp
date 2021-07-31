package com.katyrin.testmovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.terrakok.cicerone.Router
import com.katyrin.testmovieapp.model.interactor.MainInteractor
import com.katyrin.testmovieapp.presenter.home.HomePresenter
import com.katyrin.testmovieapp.presenter.home.HomeView
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class HomePresenterUnitTest {

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var homePresenter: HomePresenter? = null
    private val mainInteractor: MainInteractor = mock()
    private val homeView: HomeView = mock()
    private val router: Router = mock()

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        homePresenter = HomePresenter(router, mainInteractor)
        homePresenter?.attachView(homeView)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        homePresenter = null
    }

    @ObsoleteCoroutinesApi
    @Test
    fun shouldShowNormalStateWhenGetFilms() = runBlocking(mainThreadSurrogate) {
        Mockito.`when`(mainInteractor.getFilms()).thenReturn(listOf())
        homePresenter?.getFilms()
        verify(homeView).showNormalState()
    }

    @ObsoleteCoroutinesApi
    @Test
    fun shouldShowNormalStateWhenGetFilmsByGenre() = runBlocking(mainThreadSurrogate) {
        Mockito.`when`(mainInteractor.getFilmsByGenre(null)).thenReturn(listOf())
        homePresenter?.getFilmsByGenre()
        verify(homeView).showNormalState()
    }

    @ObsoleteCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun shouldShowEmptyListWhenThrow() = runBlocking(mainThreadSurrogate) {
        val fakeException = Mockito.mock(IOException::class.java)
        Mockito.`when`(mainInteractor.getFilms()).thenThrow(fakeException)
        homePresenter?.getFilms()
        verify(homeView).showEmptyList()
    }
}