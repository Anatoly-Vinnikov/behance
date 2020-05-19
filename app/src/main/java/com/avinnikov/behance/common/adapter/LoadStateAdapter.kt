package com.avinnikov.behance.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.avinnikov.behance.common.paging.LoadState
import com.avinnikov.behance.databinding.RecyclerLoadStateItemBinding
import kotlinx.android.synthetic.main.recycler_load_state_item.view.*

class LoadStateAdapter(
    private val clickListener: LoadStateClickListener
) : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(loadState) {
            if (field != loadState) {
                val displayOldItem = displayLoadStateAsItem(field)
                val displayNewItem = displayLoadStateAsItem(loadState)

                if (displayOldItem && !displayNewItem) {
                    notifyItemRemoved(0)
                } else if (displayNewItem && !displayOldItem) {
                    notifyItemInserted(0)
                } else if (displayOldItem && displayNewItem) {
                    notifyItemChanged(0)
                }
                field = loadState
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadStateViewHolder {
        val inflatedView = RecyclerLoadStateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) {
        holder.bindTo(loadState)

        holder.itemView.loadStateRetryButton.setOnClickListener {
            if (holder.absoluteAdapterPosition != RecyclerView.NO_POSITION
                && loadState is LoadState.Error
            ) {
                clickListener.onRetryClicked((loadState as LoadState.Error).page)
            }
        }
    }

    override fun getItemCount(): Int = if (displayLoadStateAsItem(loadState)) 1 else 0

    private fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error
    }

    inner class LoadStateViewHolder(binding: ViewDataBinding) :
        BindingViewHolder<LoadState>(binding) {
        override fun bindTo(data: LoadState) {
            (binding as RecyclerLoadStateItemBinding).loadState = data
        }
    }
}

inline fun loadStateAdapter(
    crossinline onRetryClicked: (page: Int) -> Unit
) = LoadStateAdapter(
    clickListener = object :
        LoadStateClickListener {
        override fun onRetryClicked(page: Int) {
            onRetryClicked.invoke(page)
        }
    }
)