package com.example.presentation.core

import android.content.Context
import com.example.domain.core.error.AppError
import com.example.domain.core.error.HotpepperErrorInfo
import com.example.presentation.R

fun Throwable.getReadableMessage(context: Context): String = when (this) {
    is AppError.ApiException.NetworkException -> context.getString(R.string.error_network)
    is AppError.ApiException.TimeoutException -> context.getString(R.string.error_network)
    is AppError.ApiException.HttpException -> context.getString(R.string.error_network)
    is AppError.ApiException.HotpepperException -> when (this.errors.first()) {
        HotpepperErrorInfo.SERVER_ERROR -> context.getString(R.string.error_server)
        HotpepperErrorInfo.API_KEY_OR_IP_ADDRESS_AUTH_ERROR -> context.getString(R.string.error_api_key_or_ip_address)
        HotpepperErrorInfo.ILLEGAL_PARAMETER -> context.getString(R.string.error_illegal_parameter)
        else -> context.getString(R.string.error_unknown)
    }
    else -> context.getString(R.string.error_unknown)
}
