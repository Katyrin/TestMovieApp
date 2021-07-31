package com.katyrin.testmovieapp

import com.katyrin.testmovieapp.model.data.FilmDTO
import com.katyrin.testmovieapp.model.data.RecyclerData
import com.katyrin.testmovieapp.presenter.home.FilmsMapper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class FilmsMapperParametrizedTest(
    private val target: List<FilmDTO>,
    private val expected: List<RecyclerData>
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                listOf(
                    FilmDTO(genres = listOf("drama", "comedy", "thriller")),
                    FilmDTO(genres = listOf("drama", "comedy", "melodrama")),
                    FilmDTO(genres = listOf("detective", "horror"))),
                listOf(
                    RecyclerData.Header("Жанры"),
                    RecyclerData.Genre("comedy"),
                    RecyclerData.Genre("detective"),
                    RecyclerData.Genre("drama"),
                    RecyclerData.Genre("horror"),
                    RecyclerData.Genre("melodrama"),
                    RecyclerData.Genre("thriller"),
                    RecyclerData.Header("Фильмы")
                ),
            ),
            arrayOf(
                listOf(
                    FilmDTO(genres = listOf("home", "car", "train")),
                    FilmDTO(genres = listOf("car")),
                    FilmDTO(genres = listOf("plane", "home"))),
                listOf(
                    RecyclerData.Header("Жанры"),
                    RecyclerData.Genre("car"),
                    RecyclerData.Genre("home"),
                    RecyclerData.Genre("plane"),
                    RecyclerData.Genre("train"),
                    RecyclerData.Header("Фильмы")
                ),
            ),
            arrayOf(
                listOf(
                    FilmDTO(genres = listOf("9", "5", "4")),
                    FilmDTO(genres = listOf("7", "8", "9")),
                    FilmDTO(genres = listOf("1", "4"))),
                listOf(
                    RecyclerData.Header("Жанры"),
                    RecyclerData.Genre("1"),
                    RecyclerData.Genre("4"),
                    RecyclerData.Genre("5"),
                    RecyclerData.Genre("7"),
                    RecyclerData.Genre("8"),
                    RecyclerData.Genre("9"),
                    RecyclerData.Header("Фильмы")
                ),
            )
        )
    }

    @Test
    fun shouldConvertListRecyclerDataWhenGetBaseRecyclerData() {
        val filmsMapper = FilmsMapper()
        val list = filmsMapper.getBaseRecyclerData(target)

        Assert.assertEquals(expected, list)
    }

    @Test
    fun shouldEqualsSizeWhenGetBaseRecyclerData() {
        val filmsMapper = FilmsMapper()
        val list = filmsMapper.getBaseRecyclerData(target)

        Assert.assertEquals(list.size, expected.size)
    }
}
