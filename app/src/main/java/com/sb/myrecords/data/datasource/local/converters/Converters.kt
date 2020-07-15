package com.sb.myrecords.data.datasource.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sb.myrecords.data.entities.User

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.local.converters
 * My Records
 *
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    @TypeConverter fun fromUser(user: User): String = Gson().toJson(user)

    @TypeConverter
    fun toUser(json: String): User {
        val type = object : TypeToken<User>() {}.type
        return Gson().fromJson(json, type)
    }

}