package com.nan.xarch.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Fragment基类
 */
abstract class BaseFragment : Fragment(), IGetPageName {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    /**
     * 添加Disposable
     */
    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}