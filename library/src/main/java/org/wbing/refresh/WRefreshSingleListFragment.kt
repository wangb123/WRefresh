package org.wbing.refresh

import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.wbing.view.list.WAdapter
import org.wbing.view.list.WMultiAdapter
import org.wbing.view.list.WRecyclerView

/**
 * @author wangbing
 * @date 2018/8/23
 */
abstract class WRefreshSingleListFragment<Data, Binding : ViewDataBinding> : WRefreshAdapterListFragment<Data, Binding>() {

    private var defaultAdapter: WAdapter<Data, Binding> = WAdapter.SimpleAdapter<Data, Binding>(this.variableId(), this.holderLayout())
    private var defaultLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun initRecyclerView(view: WRecyclerView) {
        view.layoutManager = layoutManager()
        view.adapter = adapter()
    }

    override fun adapter(): WAdapter<Data, Binding> {
        return defaultAdapter
    }

    override fun layoutManager(): RecyclerView.LayoutManager {
        return defaultLayoutManager
    }

    protected abstract fun variableId(): Int
    protected abstract fun holderLayout(): Int
}
