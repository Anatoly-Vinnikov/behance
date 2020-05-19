package com.avinnikov.behance.ui.main.viewmodel

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import com.avinnikov.behance.common.navigator.NavigationCommand

class NavigatorViewModel : ViewModel() {
    var destination: NavigationCommand? = null

    fun saveDestination(@IdRes destinationId: Int, inclusive: Boolean) {
        destination = NavigationCommand.BackTo(destinationId, inclusive)
    }
}