package com.avinnikov.behance.data.error

import android.content.Context
import com.avinnikov.behance.R
import com.avinnikov.behance.common.error.BaseErrorHandler
import retrofit2.HttpException

class UserErrorHandler(context: Context) : BaseErrorHandler(context) {
    override fun getError(throwable: Throwable): ErrorData {
        return when (val error = super.getError(throwable)) {
            is ErrorData.Unknown -> getUserError(error)
            is ErrorData.UnknownHttpException -> getUserError(error.exception)
            else -> error
        }
    }

    private fun getUserError(throwable: Throwable): ErrorData {
        return when (throwable) {
            is HttpException -> {
                // TODO: use it if you need to handle message from server
                //val message = throwable.message ?: context.getString(R.string.unknown_error)
                when (throwable.code()) {
                    //404 -> ErrorData.UserNotFound(message)
                    404 -> ErrorData.UserNotFound(
                        context.getString(R.string.user_not_found_error)
                    )
                    else -> ErrorData.UnknownHttpException(throwable)
                }
            }
            else -> ErrorData.Unknown(
                -1,
                context.getString(R.string.unknown_error)
            )
        }
    }
}