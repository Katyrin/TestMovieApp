package com.katyrin.testmovieapp.screen

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.katyrin.testmovieapp.R

object ContentScreen : Screen<ContentScreen>() {

    val imageView = KImageView { withId(R.id.image_view) }
    val nameTextView = KTextView { withId(R.id.name_text_view) }
    val localNameTextView = KTextView { withId(R.id.localized_name_text_view) }
    val yearTextView = KTextView { withId(R.id.year_text_view) }
    val ratingTextView = KTextView { withId(R.id.rating_text_view) }
    val descriptionTextView = KTextView { withId(R.id.description_text_view) }
}