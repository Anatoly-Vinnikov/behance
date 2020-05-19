package com.avinnikov.behance.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.avinnikov.behance.common.viewmodel.Event

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) = observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) })