package com.katyrin.testmovieapp.presenter.home

import com.github.terrakok.cicerone.Router
import com.katyrin.testmovieapp.model.data.FilmsDTO
import com.katyrin.testmovieapp.model.repository.FilmsRepository
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: Router,
    private val filmsRepository: FilmsRepository,
    private val filmsMapper: FilmsMapper
) : MvpPresenter<HomeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showLoadingState()
        getMovies()
    }

    private fun getMovies() {
        filmsRepository.getFilms(callBackFilms)
    }

    private val callBackFilms = object : Callback<FilmsDTO> {
        override fun onResponse(call: Call<FilmsDTO>, response: Response<FilmsDTO>) {
            val serverResponse: FilmsDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) checkResponse(serverResponse)
            else viewState.showError(SERVER_ERROR)
        }

        override fun onFailure(call: Call<FilmsDTO>, throwable: Throwable) {
            viewState.showError(throwable.message)
        }

        private fun checkResponse(response: FilmsDTO) {
            if (response.films.isNullOrEmpty())
                viewState.showError(CORRUPTED_DATA)
            else {
                viewState.showNormalState()
                viewState.showRecyclerView(filmsMapper.mapIntoRecyclerData(response.films))
            }
        }
    }

    private companion object {
        const val SERVER_ERROR = "Ошибка сервера"
        const val CORRUPTED_DATA = "Неполные данные"
    }
}