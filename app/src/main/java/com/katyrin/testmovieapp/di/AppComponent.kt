package com.katyrin.testmovieapp.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.katyrin.testmovieapp.App
import com.katyrin.testmovieapp.di.modules.ContentModule
import com.katyrin.testmovieapp.di.modules.HomeModule
import com.katyrin.testmovieapp.di.modules.MainModule
import com.katyrin.testmovieapp.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        HomeModule::class,
        NetworkModule::class,
        ContentModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        fun build(): AppComponent
    }
}