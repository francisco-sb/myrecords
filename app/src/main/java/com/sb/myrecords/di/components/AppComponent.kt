package com.sb.myrecords.di.components

import android.app.Application
import com.sb.myrecords.App
import com.sb.myrecords.di.modules.AppModule
import com.sb.myrecords.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.di.components
 * My Records
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

}