package com.avinnikov.behance.ui.binding

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.avinnikov.behance.common.paging.LoadState

object LoadStateBindingAdapter {
    @JvmStatic
    @BindingAdapter("loadState")
    fun setLoadStateVisibility(
        view: AppCompatTextView,
        loadState: LoadState
    ) {
        if (loadState is LoadState.Error) {
            view.apply {
                text = loadState.error.message
                visibility = View.VISIBLE
            }
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("loadState")
    fun setLoadStateVisibility(
        view: ProgressBar,
        loadState: LoadState
    ) {
        view.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("loadState")
    fun setLoadStateVisibility(
        view: AppCompatButton,
        loadState: LoadState
    ) {
        view.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
    }
}