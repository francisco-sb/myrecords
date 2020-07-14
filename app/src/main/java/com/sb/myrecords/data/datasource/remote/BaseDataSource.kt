package com.sb.myrecords.data.datasource.remote

import com.sb.myrecords.data.datasource.Result
import retrofit2.Response
import java.lang.Exception

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.data.datasource.remote
 * My Records
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(
                    body
                )
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }
}