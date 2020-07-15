package com.sb.myrecords.di.modules

import com.sb.myrecords.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.di.modules
 * My Records
 */
@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
