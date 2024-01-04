package com.example.fleeapp.common


suspend fun <T> trySuspended(action: suspend () -> T): Result<T> {
    return try {
        Result.success(action.invoke())
    } catch (e: Exception) {
        // TODO shiver me timbers
        Result.failure(e)
    }
}