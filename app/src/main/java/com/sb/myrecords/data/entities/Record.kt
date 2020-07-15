package com.sb.myrecords.data.entities

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sb.myrecords.data.datasource.local.converters.Converters

/**
 * Created by Sb on 13/07/2020
 * com.sb.myrecords.data.entities
 * My Records
 */
@Entity(tableName = "records", primaryKeys = ["description", "recordVideo", "previewImg"])
@TypeConverters(Converters::class)
data class Record(
    var profile: User,
    var description: String,
    @SerializedName("record_video")
    var recordVideo: String,
    @SerializedName("preview_img")
    var previewImg: String
)