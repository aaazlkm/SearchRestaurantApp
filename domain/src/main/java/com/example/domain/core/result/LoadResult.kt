package com.example.domain.core.result

import com.example.domain.core.error.AppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

sealed class LoadResult<out T> {
    object Loading : LoadResult<Nothing>()
    data class Success<T>(val value: T) : LoadResult<T>()
    data class Error(val e: AppError) : LoadResult<Nothing>() {
        constructor(e: Throwable) : this(
            if (e is AppError) {
                e
            } else {
                AppError.UnknownException(e)
            }
        )
    }

    val isLoading: Boolean
        get() = this is Loading

    val isError: Boolean
        get() = this is Error

    val valueOrNull: T?
        get() = when (this) {
            is Success -> value
            else -> null
        }

    val throwableOrNull: Throwable?
        get() = when (this) {
            is Error -> e
            else -> null
        }

    fun printStackTraceIfError() {
        if (this is Error) {
            this.e.printStackTrace()
        }
    }
}

fun <T> wrapByLoading(block: suspend () -> Result<T>): Flow<LoadResult<T>> = flow {
    when (val result = block()) {
        is Result.Success -> emit(LoadResult.Success(result.value))
        is Result.Error -> emit(LoadResult.Error(result.e))
    }
}.onStart { emit(LoadResult.Loading) }
