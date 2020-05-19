package com.avinnikov.behance.common.paging

import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T : Any> boundaryCallback(
    startPage: Int = 2,
    refresh: suspend (page: Int) -> Unit
) = object : PagedList.BoundaryCallback<T>() {

    private var lastRequestedPage = startPage

    override fun onZeroItemsLoaded() {
        invokeRefresh()
    }

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        invokeRefresh()
    }

    private fun invokeRefresh() {
        CoroutineScope(Dispatchers.IO).launch {
            refresh.invoke(lastRequestedPage++)
        }
    }
}