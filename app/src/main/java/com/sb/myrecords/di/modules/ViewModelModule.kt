package com.sb.myrecords.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sb.myrecords.di.ViewModelFactory
import com.sb.myrecords.di.ViewModelKey
import com.sb.myrecords.presentation.myrecords.MyRecordsViewModel
import com.sb.myrecords.presentation.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.di.modules
 * My Records
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyRecordsViewModel::class)
    abstract fun bindMyRecordsViewModel(viewModel: MyRecordsViewModel): ViewModel

    /*@Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel*/

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}