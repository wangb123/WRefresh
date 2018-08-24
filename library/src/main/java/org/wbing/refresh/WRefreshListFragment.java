package org.wbing.refresh;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.wbing.view.list.WAdapter;
import org.wbing.view.list.WRecyclerView;

import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public abstract class WRefreshListFragment<Data, Binding extends ViewDataBinding> extends WRefreshFragment {
    RecyclerView.LayoutManager layoutManager;
    int page;

    @Override
    public final void initContainView(View containView) {
        initListView((WRecyclerView) containView);
    }

    @Override
    boolean enableRefresh() {
        return true;
    }

    @Override
    boolean enableLoadMore() {
        return true;
    }

    @Override
    final int containId() {
        return R.layout.w_refresh_list;
    }


    @Override
    public void recycle() {

    }

    public void initListView(WRecyclerView list) {
        list.setLayoutManager(layoutManager());
        list.setAdapter(adapter());
    }

    public RecyclerView.LayoutManager layoutManager() {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(getContext());
        }
        return layoutManager;
    }

    public void setList(List<Data> list) {
        adapter().setList(list);
        if (list == null) {
            showError();
        } else if (list.isEmpty()) {
            showEmpty();
        } else {
            showContent();
            page = 2;
        }
    }

    public void addList(List<Data> data) {
        adapter().addItems(data);
        page++;
    }

    public void setNoMoreData(boolean noMore) {
        getBinding().refresh.setNoMoreData(noMore);
    }

    public int getPage() {
        return page;
    }

    public abstract WAdapter<Data, Binding> adapter();
}
