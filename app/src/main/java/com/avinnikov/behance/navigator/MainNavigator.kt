package com.avinnikov.behance.navigator

import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.avinnikov.behance.R
import com.avinnikov.behance.common.navigator.NavigationCommand
import com.avinnikov.behance.common.navigator.Navigator
import com.avinnikov.behance.ui.main.viewmodel.NavigatorViewModel

class MainNavigator(private val activity: AppCompatActivity) :
    Navigator {

    private val viewModel by activity.viewModels<NavigatorViewModel>()

    override val navigator: NavController by lazy {
        activity.findNavController(R.id.mainNavigationHostFragment)
    }

    override fun currentFragment(): Fragment {
        val navHostFragment = activity.supportFragmentManager.fragments.first() as NavHostFragment
        return navHostFragment.childFragmentManager.fragments.last()
    }

    fun to(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> to(command.directions, command.needSave, command.inclusive)
            is NavigationCommand.Back -> back()
            is NavigationCommand.BackTo -> back(
                command.destinationId,
                command.inclusive
            )
            is NavigationCommand.ToSaved -> viewModel.destination?.let { to(it) }
        }
    }

    private fun to(directions: NavDirections, needSave: Boolean, inclusive: Boolean) {
        if (needSave) navigator.currentDestination?.id?.let {
            viewModel.saveDestination(it, inclusive)
        }

        navigator.navigate(directions)
    }

    private fun back() {
        navigator.popBackStack()
    }

    private fun back(@IdRes destinationId: Int, inclusive: Boolean) {
        navigator.popBackStack(destinationId, inclusive)
    }
}