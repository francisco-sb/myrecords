package com.sb.myrecords.data.entities

import com.google.gson.annotations.SerializedName
import com.sb.myrecords.data.entities.User

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.data.datasource.remote.responses
 * My Records
 */
data class Record(
    var profile: User,
    var description: String,
    @SerializedName("record_video") var recordVideo: String,
    @SerializedName("preview_img") var previewImg: String
)