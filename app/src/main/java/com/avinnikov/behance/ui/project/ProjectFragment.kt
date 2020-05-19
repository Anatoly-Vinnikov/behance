package com.avinnikov.behance.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.avinnikov.behance.R
import com.avinnikov.behance.extensions.inflate
import com.avinnikov.behance.extensions.logd
import com.avinnikov.behance.extensions.observeEvent
import com.avinnikov.behance.extensions.showToast
import com.avinnikov.behance.ui.project.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProjectFragment : Fragment() {

    private val args: ProjectFragmentArgs by navArgs()
    private val viewModel by viewModel<ProjectViewModel> { parametersOf(args.projectId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        container?.inflate(
            R.layout.fragment_project
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUi()
    }

    private fun setupToolbar() {
        activity?.title = getString(R.string.fragment_project_title)
    }

    private fun subscribeUi() {
        viewModel.project.observeEvent(viewLifecycleOwner) {
            logd(it.toString())
        }

        viewModel.errorMessage.observeEvent(viewLifecycleOwner) {
            showToast(it)
        }
    }
}