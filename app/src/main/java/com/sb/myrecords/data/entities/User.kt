package com.sb.myrecords.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.data.entities
 * My Records
 */
data class User(
    val name: String,
    @SerializedName("user_name") val username: String,
    val biography: String,
    val followers: Int? = 0,
    val followed: Int? = 0,
    val views: Int? = 0,
    @SerializedName("profile_picture") val profilePicture: String
)