package org.wbing.refresh

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.view.View
import android.view.ViewStub
import com.scwang.smartrefresh.layout.constant.RefreshState

import org.wbing.base.ui.impl.WFrag
import org.wbing.refresh.databinding.WRefreshBinding

import java.util.Objects

/**
 * @author wangbing
 * @date 2018/8/23
 */
open abstract class WRefreshFragment : WFrag<WRefreshBinding>() {

    override fun layoutId(): Int {
        return R.layout.w_refresh
    }

    override fun onPostViewCreate() {
        super.onPostViewCreate()
//        //初始化布局文件
//        if (!Objects.requireNonNull<WRefreshBinding>(getBinding()).contain.isInflated) {
//            Objects.requireNonNull<ViewStub>(getBinding()!!.contain.viewStub).setLayoutResource(containId())
//            getBinding()!!.contain.setOnInflateListener { stub, inflated -> initContainView(inflated) }
//            getBinding()!!.contain.viewStub!!.inflate()
//        }
//        //设置是否可以刷新
//        getBinding()!!.refresh.setEnableRefresh(enableRefresh())
//        //设置是否可以加载更多
//        getBinding()!!.refresh.setEnableLoadMore(enableLoadMore())
//
//        //设置刷新回调
//        getBinding()!!.refresh.setOnRefreshListener { refreshLayout -> refresh() }
//        getBinding()!!.content.setRetryListener { v ->
//            refresh()
//            showLoading()
//        }
//
//        //设置加载更多回调
//        getBinding()!!.refresh.setOnLoadMoreListener { refreshLayout -> loadMore() }


        //设置refresh
        getBinding()?.refresh?.setEnableRefresh(false)?.setEnableLoadMore(false)?.setOnRefreshListener { refresh() }?.setOnLoadMoreListener { loadMore() }
        //点击重试
        getBinding()?.content?.setRetryListener { refresh() }

        //加载布局
        getBinding()?.contain?.viewStub?.layoutResource = containId()
        getBinding()?.contain?.setOnInflateListener { _, v -> initContainView(v) }
        getBinding()?.contain?.viewStub?.inflate()

    }

    public open fun initContainView(view: View) {}

    /**
     * 加载成功，显示内容或者空界面
     */
    public open fun showContent(hasMore: Boolean) {
        getBinding()?.refresh?.setEnableRefresh(true)?.setEnableLoadMore(hasMore)
        getBinding()!!.content.showContent()
    }

    /**
     * 加载成功，显示内容或者空界面
     */
    public open fun showEmpty() {
        getBinding()!!.refresh.setEnableRefresh(true)?.setEnableLoadMore(false)
        getBinding()!!.content.showEmpty()
    }

    public open fun showEmpty(text: String) {
        getBinding()!!.content.setEmptyText(text)
        showEmpty()
    }

    public open fun showError() {
        getBinding()!!.content.showError()
    }

    public open fun showError(text: String) {
        getBinding()!!.content.setErrorText(text)
        showError()
    }

    public open fun showError(@DrawableRes resId: Int) {
        getBinding()!!.content.setErrorImage(resId)
        showError()
    }

    public fun showError(text: String, @DrawableRes resId: Int) {
        getBinding()!!.content.setErrorText(text).setErrorImage(resId)
        showError()
    }

    public open fun showLoading() {
        if (canShowLoading()) {
            getBinding()!!.content.showLoading()
        }
    }

    public open fun loadComplete() {
        when (getBinding()!!.refresh.state) {
            RefreshState.Loading -> getBinding()!!.refresh.finishLoadMore()
            RefreshState.Refreshing -> getBinding()!!.refresh.finishRefresh()
            else -> {
            }
        }
    }

    public abstract fun containId(): Int
    public abstract fun canShowLoading(): Boolean
    public abstract fun refresh()
    public abstract fun loadMore()
}
