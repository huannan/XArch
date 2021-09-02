package com.nan.xarch.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.base.BaseItemViewDelegate
import com.nan.xarch.databinding.ItemTest2Binding

class Test3ViewDelegate : BaseItemViewDelegate<Test3ViewData, Test3ViewDelegate.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(ItemTest2Binding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Test3ViewData) {
        super.onBindViewHolder(holder, item)
        holder.viewBinding.tvTest.text = item.value.firstName + item.value.lastName
    }

    class ViewHolder(val viewBinding: ItemTest2Binding) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}