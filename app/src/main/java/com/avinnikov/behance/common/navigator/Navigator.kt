package com.avinnikov.behance.common.navigator

import androidx.fragment.app.Fragment
import androidx.navigation.NavController

interface Navigator {
    val navigator: NavController

    fun currentFragment(): Fragment
}