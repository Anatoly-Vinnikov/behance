package com.avinnikov.behance.common.error

import android.content.Context
import com.avinnikov.behance.R
import com.avinnikov.behance.data.error.ErrorData
import retrofit2.HttpException
import java.io.IOException

open class BaseErrorHandler(val context: Context) : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorData {
        return when (throwable) {
            is IOException -> ErrorData.NoConnection(
                context.getString(R.string.no_connection_error)
            )
            is HttpException -> {
                // TODO: use it if you need to handle message from server
                //val message = throwable.message ?: context.getString(R.string.unknown_error)
                when (throwable.code()) {
                    /*403 -> ErrorData.Forbidden(message)
                    429 -> ErrorData.TooManyRequests(message)
                    500 -> ErrorData.InternalServerError(message)
                    503 -> ErrorData.ServiceUnavailable(message)*/
                    403 -> ErrorData.Forbidden(
                        context.getString(R.string.unknown_error)
                    )
                    429 -> ErrorData.TooManyRequests(
                        context.getString(R.string.too_many_requests_error)
                    )
                    500 -> ErrorData.InternalServerError(
                        context.getString(R.string.internal_server_error)
                    )
                    503 -> ErrorData.ServiceUnavailable(
                        context.getString(R.string.service_unavailable_error)
                    )
                    else -> ErrorData.UnknownHttpException(
                        throwable
                    )
                }
            }
            is ErrorData.Unknown -> throwable
            else -> ErrorData.Unknown(
                -1,
                context.getString(R.string.unknown_error)
            )
        }
    }
}