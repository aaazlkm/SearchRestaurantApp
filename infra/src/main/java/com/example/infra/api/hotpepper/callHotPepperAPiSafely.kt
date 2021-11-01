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
                throw AppError.ApiException.NetworkException(
                    throwable
                )
            is HttpException ->
                throw AppError.ApiException.HttpException(
                    throwable,
                    throwable.code(),
                    throwable.message()
                )
            is SocketTimeoutException ->
                throw AppError.ApiException.TimeoutException(
                    throwable
                )
            else ->
                throw AppError.ApiException.UnknownException(
                    throwable
                )
        }
    }
    return when (val errors = response.results.errors) {
        null -> response
        else -> throw AppError.ApiException.HotpepperException(
            errors.map { HotpepperErrorInfoMapper.fromData(it) }
        )
    }
}
