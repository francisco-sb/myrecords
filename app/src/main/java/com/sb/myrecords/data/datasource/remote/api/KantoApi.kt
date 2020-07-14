package com.sb.myrecords.data.datasource.remote.api

import com.sb.myrecords.data.entities.Record
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.data.datasource.remote.api
 * My Records
 */
interface KantoApi {

    @GET("/v2/5e669952310000d2fc23a20e")
    suspend fun getData(): Response<List<Record>>

}