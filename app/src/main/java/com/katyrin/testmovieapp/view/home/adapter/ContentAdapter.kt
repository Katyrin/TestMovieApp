package com.katyrin.testmovieapp.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katyrin.testmovieapp.databinding.ItemFilmBinding
import com.katyrin.testmovieapp.databinding.ItemGenreBinding
import com.katyrin.testmovieapp.databinding.ItemHeaderBinding
import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.utils.setImageByUri

class ContentAdapter(
    private var onClickFilm: (FilmDTO) -> Unit,
    private var onClickGenres: (String) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var data: List<RecyclerData> = listOf()

    fun updateData(newData: List<RecyclerData>) {
        data = newData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_GENRE -> GenreViewHolder(
                ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> FilmViewHolder(
                ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int =
        when (data[position]) {
            is RecyclerData.Header -> TYPE_HEADER
            is RecyclerData.Genre -> TYPE_GENRE
            else -> TYPE_FILM
        }

    inner class HeaderViewHolder(
        private val itemBinding: ItemHeaderBinding
    ) : BaseViewHolder(itemBinding.root) {
        override fun bind(dataItem: RecyclerData): Unit =
            with(dataItem as RecyclerData.Header) {
                itemBinding.headerTextView.text = header
            }
    }

    inner class GenreViewHolder(
        private val itemBinding: ItemGenreBinding
    ) : BaseViewHolder(itemBinding.root) {
        override fun bind(dataItem: RecyclerData): Unit =
            with(dataItem as RecyclerData.Genre) {
                itemBinding.genreTextView.text = genre
                itemBinding.root.setOnClickListener { onClickGenres(genre) }
            }
    }

    inner class FilmViewHolder(
        private val itemBinding: ItemFilmBinding
    ) : BaseViewHolder(itemBinding.root) {
        override fun bind(dataItem: RecyclerData): Unit =
            with(dataItem as RecyclerData.Film) {
                itemBinding.nameTextView.text = filmDTO.localizedName
                itemBinding.imageView.setImageByUri(filmDTO.imageUrl)
                itemBinding.root.setOnClickListener { onClickFilm(filmDTO) }
            }
    }

    private companion object {
        const val TYPE_HEADER = 0
        const val TYPE_GENRE = 1
        const val TYPE_FILM = 2
    }
}