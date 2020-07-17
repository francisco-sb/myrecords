package com.sb.myrecords.domain.repository

import com.sb.myrecords.data.datasource.local.dao.UserDao
import com.sb.myrecords.data.entities.User
import javax.inject.Inject

/**
 * Created by Sb on 16/07/2020
 * com.sb.myrecords.domain.repository
 * My Records
 */
class UserRepository @Inject constructor(
    private val dao: UserDao
) {

    suspend fun insert(user: User) {
        dao.insert(user)
    }

    fun getUser(id: Int) = dao.getUser(id)

}