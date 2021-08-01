package com.katyrin.testmovieapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.katyrin.testmovieapp.screen.ContentScreen
import com.katyrin.testmovieapp.screen.HomeScreen
import com.katyrin.testmovieapp.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest : KTestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkRecyclerViewContentTest() {

        run {
            step("First step") {
                HomeScreen {
                    recyclerView {
                        isDisplayed()
                        childAt<HomeScreen.FilmItem>(16) {
                            nameTextView {
                                isDisplayed()
                                hasText("Бойцовский клуб")
                            }
                            imageView {
                                isDisplayed()
                            }
                            click()
                        }
                    }
                }
            }
            step("Second step") {
                ContentScreen {
                    imageView {
                        isDisplayed()
                    }
                    nameTextView {
                        isDisplayed()
                        hasText("Fight Club")
                    }
                    localNameTextView {
                        isDisplayed()
                        hasText("Бойцовский клуб")
                    }
                    yearTextView {
                        isDisplayed()
                        hasText("1999")
                    }
                    ratingTextView {
                        isDisplayed()
                        hasText("8.656")
                    }
                    descriptionTextView {
                        isDisplayed()
                        hasText("Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из мучительно скучной жизни, клерк встречает некоего Тайлера Дардена, харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное, ради чего стоит жить. Пройдет немного времени, и вот уже главные герои лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский Клуб, который имеет огромный успех. Но в концовке фильма всех ждет шокирующее открытие, которое может привести к непредсказуемым событиям…")
                    }
                    pressBack()
                }
            }
            step("Third step") {
                HomeScreen {
                    recyclerView {
                        isDisplayed()
                        childAt<HomeScreen.GenreItem>(3) {
                            genreTextView {
                                isDisplayed()
                                hasText("детектив")
                            }
                            click()
                        }
                    }
                }
            }
            step("Fourth step") {
                HomeScreen {
                    recyclerView {
                        isDisplayed()
                        childAt<HomeScreen.FilmItem>(16) {
                            nameTextView {
                                isDisplayed()
                                hasText("Зеленая миля")
                            }
                            imageView {
                                isDisplayed()
                            }
                            click()
                        }
                    }
                }
            }
            step("Fifth step") {
                ContentScreen {
                    imageView {
                        isDisplayed()
                    }
                    nameTextView {
                        isDisplayed()
                        hasText("The Green Mile")
                    }
                    localNameTextView {
                        isDisplayed()
                        hasText("Зеленая миля")
                    }
                    yearTextView {
                        isDisplayed()
                        hasText("1999")
                    }
                    ratingTextView {
                        isDisplayed()
                        hasText("9.064")
                    }
                    descriptionTextView {
                        isDisplayed()
                        hasText("Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор. Гигант удивил всех позже, когда выяснилось, что он обладает невероятной магической силой…")
                    }
                    pressBack()
                }
            }
            step("Sixth step") {
                HomeScreen {
                    recyclerView {
                        isDisplayed()
                        childAt<HomeScreen.GenreItem>(3) {
                            genreTextView {
                                isDisplayed()
                                hasText("детектив")
                            }
                            click()
                        }
                    }
                }
            }
            step("Seventh step") {
                HomeScreen {
                    recyclerView {
                        isDisplayed()
                        childAt<HomeScreen.FilmItem>(16) {
                            nameTextView {
                                isDisplayed()
                                hasText("Бойцовский клуб")
                            }
                            imageView {
                                isDisplayed()
                            }
                            click()
                        }
                    }
                }
            }
            step("Eighth step") {
                ContentScreen {
                    imageView {
                        isDisplayed()
                    }
                    nameTextView {
                        isDisplayed()
                        hasText("Fight Club")
                    }
                    localNameTextView {
                        isDisplayed()
                        hasText("Бойцовский клуб")
                    }
                    yearTextView {
                        isDisplayed()
                        hasText("1999")
                    }
                    ratingTextView {
                        isDisplayed()
                        hasText("8.656")
                    }
                    descriptionTextView {
                        isDisplayed()
                        hasText("Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из мучительно скучной жизни, клерк встречает некоего Тайлера Дардена, харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а саморазрушение — единственное, ради чего стоит жить. Пройдет немного времени, и вот уже главные герои лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский Клуб, который имеет огромный успех. Но в концовке фильма всех ждет шокирующее открытие, которое может привести к непредсказуемым событиям…")
                    }
                    pressBack()
                }
            }
        }
    }
}