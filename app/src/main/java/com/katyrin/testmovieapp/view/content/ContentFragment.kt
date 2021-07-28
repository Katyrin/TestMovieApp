package com.katyrin.testmovieapp.view.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.katyrin.testmovieapp.R
import com.katyrin.testmovieapp.databinding.FragmentContentBinding
import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.presenter.content.ContentPresenter
import com.katyrin.testmovieapp.presenter.content.ContentView
import com.katyrin.testmovieapp.utils.setImageByUri
import com.katyrin.testmovieapp.view.abs.AbsFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ContentFragment : AbsFragment(R.layout.fragment_content), ContentView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ContentPresenter

    @ProvidePresenter
    fun providePresenter(): ContentPresenter = presenter

    private var filmContent: FilmDTO? = null
    private var binding: FragmentContentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentContentBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun init() {
        arguments?.let { filmContent = it.getParcelable(FILM_CONTENT) }
        binding?.nameTextView?.text = filmContent?.name
        binding?.localizedNameTextView?.text = filmContent?.localizedName
        binding?.yearTextView?.text = filmContent?.year.toString()
        binding?.ratingTextView?.text = filmContent?.rating.toString()
        binding?.descriptionTextView?.text = filmContent?.description
        binding?.imageView?.setImageByUri(filmContent?.imageUrl)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        private const val FILM_CONTENT = "FILM_CONTENT"
        fun newInstance(filmDTO: FilmDTO): Fragment = ContentFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FILM_CONTENT, filmDTO)
            }
        }
    }
}