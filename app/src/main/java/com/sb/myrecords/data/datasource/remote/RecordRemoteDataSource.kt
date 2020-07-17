package com.sb.myrecords.data.datasource.remote

import com.sb.myrecords.data.datasource.BaseDataSource
import com.sb.myrecords.data.datasource.remote.api.KantoApi
import javax.inject.Inject

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.remote
 * My Records
 *
 * Works with the Kanto API to get records.
 */
class RecordRemoteDataSource @Inject constructor(private val kantoApi: KantoApi) :
    BaseDataSource() {

    suspend fun fetchData() = getResult { kantoApi.getData() }

}