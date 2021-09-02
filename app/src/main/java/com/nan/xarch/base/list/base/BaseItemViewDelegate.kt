package com.nan.xarch.base.list.base

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.list.XRecyclerView
import com.nan.xarch.base.list.multitype.ItemViewDelegate

abstract class BaseItemViewDelegate<T : BaseViewData<*>, VH : RecyclerView.ViewHolder> : ItemViewDelegate<T, VH>() {

    @CallSuper
    override fun onBindViewHolder(holder: VH, item: T) {
        holder.itemView.setOnClickListener {
            performItemClick(it, item, holder)
        }
        holder.itemView.setOnLongClickListener {
            return@setOnLongClickListener performItemLongClick(it, item, holder)
        }
    }

    /**
     * 条目点击监听
     */
    protected fun performItemClick(view: View, item: BaseViewData<*>, holder: RecyclerView.ViewHolder) {
        val recyclerView = getRecyclerView(view)
        if (null != recyclerView) {
            val position: Int = holder.absoluteAdapterPosition
            val id = holder.itemId
            recyclerView.performItemClick(view, item, position, id)
        }
    }

    /**
     * 条目长按监听
     */
    protected fun performItemLongClick(view: View, item: BaseViewData<*>, holder: RecyclerView.ViewHolder): Boolean {
        var consumed = false
        val recyclerView = getRecyclerView(view)
        if (null != recyclerView) {
            val position: Int = holder.absoluteAdapterPosition
            val id = holder.itemId
            consumed = recyclerView.performItemLongClick(view, item, position, id)
        }
        return consumed
    }

    /**
     * 子View点击监听
     */
    protected fun performItemChildViewClick(view: View, item: BaseViewData<*>, holder: RecyclerView.ViewHolder, extra: Any?) {
        val recyclerView = getRecyclerView(view)
        if (null != recyclerView) {
            val position: Int = holder.absoluteAdapterPosition
            val id = holder.itemId
            recyclerView.performItemChildViewClick(view, item, position, id, extra)
        }
    }

    /**
     * 获取装载自己的XRecyclerView
     */
    private fun getRecyclerView(child: View): XRecyclerView? {
        var recyclerView: XRecyclerView? = null
        var parent: ViewParent = child.parent
        while (parent is ViewGroup) {
            if (parent is XRecyclerView) {
                recyclerView = parent
                break
            }
            parent = parent.getParent()
        }
        return recyclerView
    }

}