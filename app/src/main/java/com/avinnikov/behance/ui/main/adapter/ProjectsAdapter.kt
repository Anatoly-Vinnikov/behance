package com.avinnikov.behance.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avinnikov.behance.common.adapter.BindingViewHolder
import com.avinnikov.behance.common.adapter.OnRecyclerClickListener
import com.avinnikov.behance.common.equality.DiffItemCallback
import com.avinnikov.behance.data.equality.ProjectEquality
import com.avinnikov.behance.data.local.Project
import com.avinnikov.behance.databinding.RecyclerProjectItemBinding

class ProjectsAdapter(
    diffCallback: DiffUtil.ItemCallback<Project>,
    private val clickListener: OnRecyclerClickListener<Project>
) : PagedListAdapter<Project, ProjectsAdapter.ProjectsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val inflatedView = RecyclerProjectItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProjectsViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        val item: Project? = getItem(position)

        item?.let {
            holder.bindTo(item)

            holder.itemView.setOnClickListener {
                if (holder.absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                    clickListener.onItemClicked(item)
                }
            }
        }
    }

    inner class ProjectsViewHolder(binding: ViewDataBinding) :
        BindingViewHolder<Project>(binding) {
        override fun bindTo(data: Project) {
            (binding as RecyclerProjectItemBinding).project = data
        }
    }
}

inline fun projectsAdapter(
    crossinline onItemClicked: (item: Project) -> Unit
): ProjectsAdapter {
    val equality = ProjectEquality()

    return ProjectsAdapter(
        DiffItemCallback(equality.getIdentityEquality(), equality.getContentEquality()),
        clickListener = object :
            OnRecyclerClickListener<Project> {
            override fun onItemClicked(item: Project) {
                onItemClicked.invoke(item)
            }
        }
    )
}