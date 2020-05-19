package com.avinnikov.behance.common.equality

import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback<T>(
    private val identityEqualityFunction: (T, T) -> Boolean,
    private val contentEqualityFunction: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        identityEqualityFunction.invoke(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        contentEqualityFunction.invoke(oldItem, newItem)
}