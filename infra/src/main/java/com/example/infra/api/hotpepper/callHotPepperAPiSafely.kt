package com.example.infra.api.hotpepper

import com.example.domain.core.error.AppError
import com.example.infra.api.hotpepper.mapper.HotpepperErrorInfoMapper
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend inline fun <T : HotpepperResponse> callHotPepperAPiSafely(crossinline apiCall: suspend () -> T): T {
    val response = try {
        apiCall.invoke()
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException ->
                throw AppError.Api.NetworkException(
                    throwable
                )
            is HttpException ->
                throw AppError.Api.HttpException(
                    throwable,
                    throwable.code(),
                    throwable.message()
                )
            is SocketTimeoutException ->
                throw AppError.Api.TimeoutException(
                    throwable
                )
            else ->
                throw AppError.Api.UnknownException(
                    throwable
                )
        }
    }
    return when (val errors = response.results.errors) {
        null -> response
        else -> throw AppError.Api.HotpepperException(
            errors.map { HotpepperErrorInfoMapper.fromData(it) }
        )
    }
}
