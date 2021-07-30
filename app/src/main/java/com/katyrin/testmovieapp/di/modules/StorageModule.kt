package com.katyrin.testmovieapp.di.modules

import android.content.Context
import androidx.room.Room
import com.katyrin.testmovieapp.model.datasource.LocalDataSource
import com.katyrin.testmovieapp.model.datasource.LocalDataSourceImpl
import com.katyrin.testmovieapp.model.storage.FilmsDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface StorageModule {

    companion object {
        private const val DB_NAME = "database.db"

        @Singleton
        @Provides
        fun provideDataBase(context: Context): FilmsDataBase =
            Room.databaseBuilder(context, FilmsDataBase::class.java, DB_NAME).build()
    }

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}