package com.avinnikov.behance.extensions

import timber.log.Timber

fun logd(message: String, tag: String = "timber") = Timber.tag(tag).d(message)