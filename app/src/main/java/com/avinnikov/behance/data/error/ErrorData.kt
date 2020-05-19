package com.avinnikov.behance.data.error

import retrofit2.HttpException

sealed class ErrorData : Throwable() {
    data class NoConnection(override val message: String) : ErrorData()

    data class Forbidden(override val message: String) : ErrorData()

    data class ProjectNotFound(override val message: String) : ErrorData()

    data class UserNotFound(override val message: String) : ErrorData()

    data class InternalServerError(override val message: String) : ErrorData()

    data class ServiceUnavailable(override val message: String) : ErrorData()

    data class TooManyRequests(override val message: String) : ErrorData()

    data class UnknownHttpException(val exception: HttpException) : ErrorData()

    data class Unknown(val code: Int, override val message: String) : ErrorData()
}