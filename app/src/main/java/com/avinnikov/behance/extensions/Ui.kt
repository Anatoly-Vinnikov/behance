package com.avinnikov.behance.extensions

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Activity.showToast(@StringRes resource: Int, duration: Int = Toast.LENGTH_SHORT) =
    showToast(getString(resource), duration)

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Fragment.showToast(@StringRes resource: Int, duration: Int = Toast.LENGTH_SHORT) =
    showToast(getString(resource), duration)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    activity?.showToast(message, duration)