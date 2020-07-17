package com.sb.myrecords.domain.repository

import com.sb.myrecords.data.datasource.local.dao.RecordDao
import com.sb.myrecords.data.datasource.remote.RecordRemoteDataSource
import com.sb.myrecords.data.resultLiveData
import javax.inject.Inject

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.domain.repository
 * My Records
 *
 * Repository module for handling data operations.
 */
class KantoRepository @Inject constructor(
    private val dao: RecordDao,
    private val remoteDataSource: RecordRemoteDataSource
) {

    val records = resultLiveData(
        databaseQuery = { dao.getRecords() },
        networkCall = { remoteDataSource.fetchData() },
        saveCallResult = { dao.insertAll(it) }
    )

}