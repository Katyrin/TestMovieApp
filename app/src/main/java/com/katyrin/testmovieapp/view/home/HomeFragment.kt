package com.katyrin.testmovieapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testmovieapp.R
import com.katyrin.testmovieapp.databinding.FragmentHomeBinding
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.presenter.home.HomePresenter
import com.katyrin.testmovieapp.presenter.home.HomeView
import com.katyrin.testmovieapp.utils.toast
import com.katyrin.testmovieapp.view.abs.AbsFragment
import com.katyrin.testmovieapp.view.content.ContentScreen
import com.katyrin.testmovieapp.view.home.adapter.ContentAdapter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class HomeFragment : AbsFragment(R.layout.fragment_home), HomeView {

    @Inject
    @InjectPresenter
    lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun providePresenter(): HomePresenter = presenter

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun init() {
        binding?.swipeRefreshLayout?.setOnRefreshListener { presenter.getFilms() }
        binding?.refreshButton?.setOnClickListener { presenter.getFilms() }
        binding?.recyclerView?.adapter = ContentAdapter(
            { filmDTO -> presenter.navigateToScreen(ContentScreen(filmDTO)) },
            { genre -> presenter.getFilmsByGenre(genre) }
        ).apply { stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW }
    }

    override fun showRecyclerView(data: List<RecyclerData>, genre: String?) {
        binding?.recyclerView?.layoutManager = getGridLayoutManager(data)
        (binding?.recyclerView?.adapter as ContentAdapter).updateData(data, genre)
    }

    private fun getGridLayoutManager(data: List<RecyclerData>): GridLayoutManager =
        GridLayoutManager(context, SPAN_TWO).apply {
            spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (data[position] is RecyclerData.Film) SPAN_ONE else SPAN_TWO
            }
        }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun showEmptyList() {
        binding?.recyclerView?.isVisible = false
        binding?.swipeRefreshLayout?.isRefreshing = false
        binding?.emptyText?.isVisible = true
        binding?.refreshButton?.isVisible = true
    }

    override fun showLoadingState() {
        binding?.recyclerView?.isVisible = false
        binding?.emptyText?.isVisible = false
        binding?.refreshButton?.isVisible = false
    }

    override fun showNormalState() {
        binding?.recyclerView?.isVisible = true
        binding?.swipeRefreshLayout?.isRefreshing = false
        binding?.emptyText?.isVisible = false
        binding?.refreshButton?.isVisible = false
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        private const val SPAN_ONE = 1
        private const val SPAN_TWO = 2
        fun newInstance(): Fragment = HomeFragment()
    }
}