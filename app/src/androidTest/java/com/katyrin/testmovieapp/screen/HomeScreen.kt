package com.katyrin.testmovieapp.screen

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.katyrin.testmovieapp.R
import org.hamcrest.Matcher

object HomeScreen : Screen<HomeScreen>() {

    val recyclerView = KRecyclerView(
        builder = { withId(R.id.recycler_view) },
        itemTypeBuilder = {
            itemType(::FilmItem)
            itemType(::GenreItem)
        }
    )

    class FilmItem(parent: Matcher<View>) : KRecyclerItem<FilmItem>(parent) {
        val imageView = KImageView(parent) { withId(R.id.image_view) }
        val nameTextView = KTextView(parent) { withId(R.id.name_text_view) }
    }

    class GenreItem(parent: Matcher<View>) : KRecyclerItem<GenreItem>(parent) {
        val genreTextView = KTextView(parent) { withId(R.id.genre_text_view) }
    }
}