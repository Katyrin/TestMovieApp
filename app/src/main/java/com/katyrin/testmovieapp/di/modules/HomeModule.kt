package com.katyrin.testmovieapp.di.modules

import com.katyrin.testmovieapp.view.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeModule {

    @ContributesAndroidInjector
    fun bindHomeFragment(): HomeFragment
}