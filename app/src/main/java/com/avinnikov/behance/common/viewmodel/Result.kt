package com.avinnikov.behance.common.viewmodel

sealed class Result {
    data class Success<T>(val value: T) : Result()

    data class Failure(val exception: Throwable) : Result()
}