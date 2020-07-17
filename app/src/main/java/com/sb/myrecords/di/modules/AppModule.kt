package com.sb.myrecords.di.modules

import android.app.Application
import com.sb.myrecords.data.datasource.local.AppDatabase
import com.sb.myrecords.data.datasource.remote.RecordRemoteDataSource
import com.sb.myrecords.data.datasource.remote.api.KantoApi
import com.sb.myrecords.di.KantoAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.di.modules
 * My Records
 */
@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideKantoApi(
        @KantoAPI okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okHttpClient, converterFactory, KantoApi::class.java)

    @Singleton
    @Provides
    fun provideRecordRemoteDataSource(kantoApi: KantoApi) = RecordRemoteDataSource(kantoApi)

    @KantoAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Singleton
    @Provides
    fun provideRecordDao(database: AppDatabase) = database.recordDao()

    //region:: PRIVATE METHODS
    private fun <T> provideService(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T {
        return createRetrofit(okHttpClient, converterFactory).create(clazz)
    }

    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KantoApi.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
    //endregion

}