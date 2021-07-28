package com.katyrin.testmovieapp.di.modules

import com.katyrin.testmovieapp.view.content.ContentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContentModule {

    @ContributesAndroidInjector
    fun bindContentFragment(): ContentFragment
}