package com.example.highcircletmdb.data.util

import android.util.Log
import com.example.highcircletmdb.domain.repository.RepositoryResponse

suspend inline fun <reified T> getResponseSafely(
    request: suspend () -> T
): RepositoryResponse<T> {
    return try {
        RepositoryResponse.Success(request())
    } catch (e: Throwable) {

        e.localizedMessage?.let { Log.d("getResponseSafely", it.toString()) }
        RepositoryResponse.Failure(e)
    }
}