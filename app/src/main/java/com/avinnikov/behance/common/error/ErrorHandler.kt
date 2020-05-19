package com.avinnikov.behance.common.error

import com.avinnikov.behance.data.error.ErrorData

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorData
}