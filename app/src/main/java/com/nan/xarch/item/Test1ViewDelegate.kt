package com.nan.xarch.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.base.BaseItemViewDelegate
import com.nan.xarch.databinding.ItemTest1Binding

class Test1ViewDelegate : BaseItemViewDelegate<Test1ViewData, Test1ViewDelegate.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(ItemTest1Binding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Test1ViewData) {
        super.onBindViewHolder(holder, item)
        holder.viewBinding.tvTest.text = item.value
        holder.viewBinding.btnAction.setOnClickListener {
            performItemChildViewClick(it, item, holder, "测试")
        }
    }

    class ViewHolder(val viewBinding: ItemTest1Binding) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}