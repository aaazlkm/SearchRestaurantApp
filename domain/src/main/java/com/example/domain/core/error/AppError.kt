package com.example.domain.core.error

sealed class AppError : RuntimeException {
    constructor()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

    sealed class Permission(cause: Throwable?) : AppError(cause) {
        class NoLocationPermissionException(message: String?) : AppError(message)
    }

    sealed class Api(cause: Throwable?) : AppError(cause) {
        class NetworkException(cause: Throwable?) : AppError(cause)
        class HttpException(cause: Throwable?, val code: Int, override val message: String) :
            AppError(cause)

        class TimeoutException(cause: Throwable?) : AppError(cause)
        class NotFoundedException(message: String?) : AppError(message)
        class ParseDataException(message: String?) : AppError(message)

        // hotpepper apiはHTTP ステータスコードが常に200で帰ってくるので別でエラーを定義
        data class HotpepperException(val errors: List<HotpepperErrorInfo>) : AppError()
        class UnknownException(cause: Throwable?) : AppError(cause)
    }

    class UnknownException(cause: Throwable?) : AppError(cause)
}

enum class HotpepperErrorInfo {
    SERVER_ERROR,
    API_KEY_OR_IP_ADDRESS_AUTH_ERROR,
    ILLEGAL_PARAMETER,
    UNKNOWN,
}

fun Throwable.toAppError(): AppError {
    return when (val error = this) {
        is AppError -> error
        else -> AppError.UnknownException(error)
    }
}
