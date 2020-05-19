package com.avinnikov.behance.common.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T>(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindTo(data: T)
}