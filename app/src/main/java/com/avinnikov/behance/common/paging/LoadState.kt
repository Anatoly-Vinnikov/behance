package com.avinnikov.behance.common.paging

sealed class LoadState {
    object Loading : LoadState()

    object Done : LoadState()

    data class Error(val error: Throwable, val page: Int) : LoadState()
}