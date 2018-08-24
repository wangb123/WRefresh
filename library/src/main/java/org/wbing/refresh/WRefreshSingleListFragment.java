package org.wbing.refresh;

import android.databinding.ViewDataBinding;

import org.wbing.view.list.WAdapter;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public abstract class WRefreshSingleListFragment<Data, Binding extends ViewDataBinding>
        extends WRefreshListFragment<Data, Binding> {

    private WAdapter<Data, Binding> adapter;


    @Override
    public WAdapter<Data, Binding> adapter() {
        if (adapter == null) {
            adapter = new WAdapter.SimpleAdapter<>(variableId(), holderLayout());
        }
        return adapter;
    }

    @Override
    public void recycle() {
        adapter.removeAll();
        adapter = null;
    }

    protected abstract int variableId();

    protected abstract int holderLayout();
}
