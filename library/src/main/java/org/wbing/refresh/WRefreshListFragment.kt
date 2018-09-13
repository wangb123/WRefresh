package org.wbing.refresh

import android.support.v7.widget.RecyclerView
import android.view.View
import org.wbing.view.list.WRecyclerView

/**
 * @author wangbing
 * @date 2018/8/23
 */
abstract class WRefreshListFragment : WRefreshFragment() {
    var recyclerView: WRecyclerView? = null
        private set

    override fun containId(): Int {
        return R.layout.w_refresh_list
    }

    override fun initContainView(view: View) {
        if (view is WRecyclerView) {
            recyclerView = view
            initRecyclerView(recyclerView!!)
        } else {
            throw RuntimeException("WRefreshListFragment 必须是一个RecyclerView，查看containId方法返回值")
        }
    }

    public abstract fun initRecyclerView(view: WRecyclerView)

}
