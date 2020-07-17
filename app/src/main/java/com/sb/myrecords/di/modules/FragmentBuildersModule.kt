package com.sb.myrecords.di.modules

import com.sb.myrecords.presentation.camera.CameraFragment
import com.sb.myrecords.presentation.myrecords.MyRecordsFragment
import com.sb.myrecords.presentation.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.di.modules
 * My Records
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMyRecordsFragment(): MyRecordsFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): CameraFragment

}
