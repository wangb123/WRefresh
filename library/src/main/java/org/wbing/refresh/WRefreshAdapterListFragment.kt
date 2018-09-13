package org.wbing.refresh

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import org.wbing.view.list.WAdapter
import org.wbing.view.list.WRecyclerView

/**
 * @author wangbing
 * @date 2018/9/11
 */
open abstract class WRefreshAdapterListFragment<Data, Binding : ViewDataBinding> : WRefreshListFragment() {

    private var page: Int = 1

    override fun initRecyclerView(view: WRecyclerView) {
        view.layoutManager = layoutManager()
        view.adapter = adapter()
    }

    override fun canShowLoading(): Boolean {
        return adapter().itemCount == 0
    }

    protected fun setList(list: List<Data>?) {
        adapter().list = list
        page = 2
    }

    protected fun addList(list: List<Data>?) {
        adapter().addItems(list)
        page++
    }

    override fun recycle() {
        adapter().removeAll()
    }

    protected fun getPage(): Int {
        return page
    }

    protected abstract fun layoutManager(): RecyclerView.LayoutManager
    protected abstract fun adapter(): WAdapter<Data, Binding>


}
