package com.sb.myrecords.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sb.myrecords.data.entities.Record

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.local.dao
 * My Records
 */
@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(records: List<Record>)

    @Query("SELECT * FROM records")
    fun getRecords(): LiveData<List<Record>>

}