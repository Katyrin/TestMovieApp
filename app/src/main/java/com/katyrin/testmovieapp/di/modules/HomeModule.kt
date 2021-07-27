package com.katyrin.testmovieapp.di.modules

import com.katyrin.testmovieapp.model.datasource.RemoteDataSource
import com.katyrin.testmovieapp.model.datasource.RemoteDataSourceImpl
import com.katyrin.testmovieapp.model.repository.FilmsRepository
import com.katyrin.testmovieapp.model.repository.FilmsRepositoryImpl
import com.katyrin.testmovieapp.view.home.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeModule {

    @ContributesAndroidInjector
    fun bindHomeFragment(): HomeFragment

    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindFilmsRepository(filmsRepositoryImpl: FilmsRepositoryImpl): FilmsRepository
}