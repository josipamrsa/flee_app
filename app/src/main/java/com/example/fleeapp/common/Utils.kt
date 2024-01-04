package com.example.fleeapp.common

import timber.log.Timber


suspend fun <T> trySuspended(action: suspend () -> T): Result<T> {
    return try {
        Result.success(action.invoke())
    } catch (e: Exception) {
        Timber.e("ERR: >> "+e)
        Result.failure(e)
    }
}