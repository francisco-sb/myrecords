package com.sb.myrecords.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sb.myrecords.data.entities.User

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.local.dao
 * My Records
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): LiveData<User>

}