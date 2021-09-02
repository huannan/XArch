package com.nan.xarch.base.list.multitype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This is a compatible version of [ItemViewDelegate].
 * @see ItemViewDelegate
 * @author Drakeet Xu
 */
abstract class ItemViewBinder<T, VH : RecyclerView.ViewHolder> : ItemViewDelegate<T, VH>() {

    final override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): VH {
        return onCreateViewHolder(context, LayoutInflater.from(context), parent)
    }

    abstract fun onCreateViewHolder(context: Context, inflater: LayoutInflater, parent: ViewGroup): VH
}
