package com.sb.myrecords.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.sb.myrecords.data.datasource.local.AppDatabase
import com.sb.myrecords.data.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Created by Sb on 15/07/2020
 * com.sb.myrecords.worker
 * My Records
 */
class SeedDatabaseWork(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {
            try {
                val user = User(1, "TestAndroide", "@testotesto", "Esta es mi biografía", 0, 0, 0,
                    "https://ks-profiles-dev.s3.amazonaws.com/photos/user/7052/image.png?AWSAccessKeyId=AKIAJCQQV35SSGNPJEKA&Signature=tECXdKygdW7cpuVzlKu8%2FIRBjPk%3D&Expires=2102109863")
                AppDatabase.getInstance(applicationContext).userDao().insert(user)

                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}