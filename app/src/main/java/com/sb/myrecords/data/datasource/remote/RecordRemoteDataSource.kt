package com.sb.myrecords.data.datasource.remote

import com.sb.myrecords.data.datasource.remote.api.KantoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.remote
 * My Records
 */
class RecordRemoteDataSource : BaseDataSource() {

    private val kantoApi: KantoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        kantoApi = retrofit.create(KantoApi::class.java)
    }

    suspend fun fetchData() = getResult { kantoApi.getData() }
}