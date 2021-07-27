package com.katyrin.testmovieapp.di.modules

import com.katyrin.testmovieapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}