package com.nan.xarch.module.acgn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nan.xarch.base.BaseFragment
import com.nan.xarch.base.list.XRecyclerView
import com.nan.xarch.base.list.base.BaseViewData
import com.nan.xarch.constant.PageName
import com.nan.xarch.databinding.FragmentAcgnBinding
import com.nan.xarch.widget.GridItemDecoration

/**
 * 二次元
 */
class AcgnFragment : BaseFragment<FragmentAcgnBinding>() {

    private val viewModel: AcgnViewModel by viewModels()
    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentAcgnBinding
        get() = FragmentAcgnBinding::inflate

    companion object {
        private const val ACGN_SPAN_COUNT = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.rvList.init(
            XRecyclerView.Config()
                .setViewModel(viewModel)
                .setPullRefreshEnable(true)
                .setPullUploadMoreEnable(true)
                .setLayoutManager(GridLayoutManager(activity, ACGN_SPAN_COUNT))
                .setItemDecoration(GridItemDecoration(activity, ACGN_SPAN_COUNT))
                .setOnItemClickListener(object : XRecyclerView.OnItemClickListener {
                    override fun onItemClick(parent: RecyclerView, view: View, viewData: BaseViewData<*>, position: Int, id: Long) {
                        Toast.makeText(context, "条目点击: ${viewData.value}", Toast.LENGTH_SHORT).show()
                    }
                })
                .setOnItemChildViewClickListener(object : XRecyclerView.OnItemChildViewClickListener {
                    override fun onItemChildViewClick(parent: RecyclerView, view: View, viewData: BaseViewData<*>, position: Int, id: Long, extra: Any?) {
                        Toast.makeText(context, "条目子View点击: $extra", Toast.LENGTH_SHORT).show()
                    }
                })
        )
    }

    @PageName
    override fun getPageName() = PageName.ACGN

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // 这里可以添加页面打点
    }
}