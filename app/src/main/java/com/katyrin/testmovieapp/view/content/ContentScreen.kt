package com.katyrin.testmovieapp.view.content

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.katyrin.testmovieapp.model.data.FilmDTO

class ContentScreen(private val filmDTO: FilmDTO) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        ContentFragment.newInstance(filmDTO)
}