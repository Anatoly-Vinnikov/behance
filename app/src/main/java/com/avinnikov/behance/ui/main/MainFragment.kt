package com.avinnikov.behance.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.avinnikov.behance.R
import com.avinnikov.behance.common.adapter.loadStateAdapter
import com.avinnikov.behance.common.navigator.NavigationCommand
import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.extensions.inflate
import com.avinnikov.behance.extensions.logd
import com.avinnikov.behance.extensions.observeEvent
import com.avinnikov.behance.navigator.MainNavigator
import com.avinnikov.behance.ui.main.adapter.projectsAdapter
import com.avinnikov.behance.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val navigator: MainNavigator by inject()
    private val adapter = projectsAdapter { onItemClicked(it) }
    private val loadStateAdapter = loadStateAdapter { onRetryClicked(it) }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(
            R.layout.fragment_main
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUi()
    }

    override fun onDestroyView() {
        mainRecyclerView.adapter = null
        super.onDestroyView()
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.app_name)
    }

    private fun setupRecyclerView() {
        mainRecyclerView.adapter = MergeAdapter(adapter, loadStateAdapter)
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mainRecyclerView.isSaveEnabled = true
    }

    private fun onItemClicked(item: Project) {
        navigator.to(
            NavigationCommand.To(
                MainFragmentDirections.toProjectFragment(item.id)
            )
        )
    }

    private fun onRetryClicked(page: Int) {
        viewModel.retry(page)
    }

    private fun subscribeUi() {
        viewModel.projects.observeEvent(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadState.observeEvent(viewLifecycleOwner) {
            loadStateAdapter.loadState = it
        }

        viewModel.navigationCommand.observeEvent(viewLifecycleOwner) {
            navigator.to(it)
        }

        viewModel.isConnected.observeEvent(viewLifecycleOwner) {
            logd("Connection state is $it")
        }
    }
}