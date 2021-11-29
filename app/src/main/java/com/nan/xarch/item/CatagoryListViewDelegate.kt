package com.nan.xarch.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.base.BaseAdapter
import com.nan.xarch.base.list.base.BaseItemViewDelegate
import com.nan.xarch.databinding.ItemCatagoryListBinding
import com.nan.xarch.widget.GridItemDecoration

class CatagoryListViewDelegate : BaseItemViewDelegate<CatagoryListViewData, CatagoryListViewDelegate.ViewHolder>() {

    private lateinit var catagoryAdapter: BaseAdapter

    companion object {
        private const val CATAGORY_SPAN_COUNT = 4
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, context: Context, parent: ViewGroup): ViewHolder {
        val holder = ViewHolder(ItemCatagoryListBinding.inflate(inflater, parent, false))
        catagoryAdapter = BaseAdapter()
        holder.viewBinding.rvCatagory.layoutManager = GridLayoutManager(context, CATAGORY_SPAN_COUNT)
        holder.viewBinding.rvCatagory.addItemDecoration(GridItemDecoration(context, CATAGORY_SPAN_COUNT))
        holder.viewBinding.rvCatagory.adapter = catagoryAdapter
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, item: CatagoryListViewData) {
        super.onBindViewHolder(holder, item)
        catagoryAdapter.setViewData(item.value)
    }

    class ViewHolder(val viewBinding: ItemCatagoryListBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    }
}