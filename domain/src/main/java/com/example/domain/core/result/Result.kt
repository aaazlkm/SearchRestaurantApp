package com.example.domain.core.result

import com.example.domain.core.error.AppError
import com.example.domain.core.error.toAppError

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val e: AppError) : Result<Nothing>() {
        constructor(e: Throwable) : this(
            if (e is AppError) {
                e
            } else {
                AppError.UnknownException(e)
            }
        )
    }

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

suspend fun <T> wrapByResult(block: suspend () -> T): Result<T> {
    return try {
        val value = block()
        Result.Success(value)
    } catch (throwable: Throwable) {
        Result.Error(throwable.toAppError())
    }
}
