package com.avinnikov.behance.common.navigator

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(
        val directions: NavDirections,
        val needSave: Boolean = false,
        val inclusive: Boolean = false
    ) : NavigationCommand()

    object Back : NavigationCommand()

    data class BackTo(
        @IdRes val destinationId: Int,
        val inclusive: Boolean = false
    ) : NavigationCommand()

    object ToSaved : NavigationCommand()
}