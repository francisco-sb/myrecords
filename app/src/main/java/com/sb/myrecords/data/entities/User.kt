package com.sb.myrecords.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.data.entities
 * My Records
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    var id: Int? = 1,
    var name: String,
    @SerializedName("user_name")
    var username: String,
    var biography: String? = "",
    var followers: Int? = 0,
    var followed: Int? = 0,
    var views: Int? = 0,
    var img: String
)