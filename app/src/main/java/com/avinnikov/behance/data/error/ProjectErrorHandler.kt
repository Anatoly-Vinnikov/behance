package com.avinnikov.behance.data.error

import android.content.Context
import com.avinnikov.behance.R
import com.avinnikov.behance.common.error.BaseErrorHandler
import retrofit2.HttpException

class ProjectErrorHandler(context: Context) : BaseErrorHandler(context) {
    override fun getError(throwable: Throwable): ErrorData {
        return when (val error = super.getError(throwable)) {
            is ErrorData.Unknown -> getProjectError(error)
            is ErrorData.UnknownHttpException -> getProjectError(error.exception)
            else -> error
        }
    }

    private fun getProjectError(throwable: Throwable): ErrorData {
        return when (throwable) {
            is HttpException -> {
                // TODO: use it if you need to handle message from server
                //val message = throwable.message ?: context.getString(R.string.unknown_error)
                when (throwable.code()) {
                    //404 -> ErrorData.ProjectNotFound(message)
                    404 -> ErrorData.ProjectNotFound(
                        context.getString(R.string.project_not_found_error)
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