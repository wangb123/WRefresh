package org.wbing.refresh

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.wbing.view.list.IMultiItem
import org.wbing.view.list.WMultiAdapter
import org.wbing.view.list.WRecyclerView
import org.wbing.view.list.databinding.WListItemMultiRootBinding

/**
 * @author wangbing
 * @date 2018/8/23
 */
abstract class WRefreshMultiListFragment<Data : IMultiItem> : WRefreshAdapterListFragment<Data, WListItemMultiRootBinding>() {
    private var defaultAdapter: WMultiAdapter<Data> = WMultiAdapter(this.variableId())
    private var defaultLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    public override fun adapter(): WMultiAdapter<Data> {
        return defaultAdapter
    }

    public override fun layoutManager(): RecyclerView.LayoutManager {
        return defaultLayoutManager
    }



    public abstract fun variableId(): Int


}
