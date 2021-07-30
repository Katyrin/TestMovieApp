package com.katyrin.testmovieapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.katyrin.testmovieapp.model.api.ApiService
import com.katyrin.testmovieapp.model.networkstatus.NetworkStateRepository
import com.katyrin.testmovieapp.model.networkstatus.NetworkStateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideApiPost(gson: Gson, client: OkHttpClient): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build().create(ApiService::class.java)

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().setLenient().create()

        @Provides
        @Singleton
        fun provideClient(): OkHttpClient =
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()

        private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"
    }

    @Singleton
    @Binds
    fun bindNetworkStateRepository(
        networkStateRepositoryImpl: NetworkStateRepositoryImpl
    ): NetworkStateRepository
}