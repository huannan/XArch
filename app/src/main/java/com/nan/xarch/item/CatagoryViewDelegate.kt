package com.nan.xarch.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.base.BaseItemViewDelegate
import com.nan.xarch.databinding.ItemCatagoryBinding

class CatagoryViewDelegate : BaseItemViewDelegate<CatagoryViewData, CatagoryViewDelegate.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(ItemCatagoryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: CatagoryViewData) {
        super.onBindViewHolder(holder, item)
        holder.viewBinding.ivCatagory.setImageResource(item.value.imageRes)
        holder.viewBinding.tvCatagory.text = item.value.title
    }

    class ViewHolder(val viewBinding: ItemCatagoryBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}