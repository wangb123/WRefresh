package org.wbing.app_refresh;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.TextUtils;

import org.wbing.app_refresh.data.ContentData;
import org.wbing.app_refresh.databinding.ItemContentBinding;
import org.wbing.base.ui.WCallback;
import org.wbing.refresh.WRefreshSingleListFragment;
import org.wbing.view.list.LineItemDecoration;
import org.wbing.view.list.WRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class SingleListFragment extends WRefreshSingleListFragment<ContentData, ItemContentBinding> {
    @Override
    protected int variableId() {
        return BR.data;
    }

    @Override
    protected int holderLayout() {
        return R.layout.item_content;
    }

    @Override
    public void refresh() {
        loadData(1);
    }

    @Override
    public void loadMore() {
        loadData(getPage());
    }

    @Override
    public void loadData() {
        refresh();
    }

    //
//    @Override
//    protected int variableId() {
//        return BR.data;
//    }
//
//    @Override
//    protected int holderLayout() {
//        return R.layout.item_content;
//    }
//
//
//    @Override
//    public void initListView(WRecyclerView list) {
//        super.initListView(list);
//        list.addItemDecoration(new LineItemDecoration());
//    }
//
//    @Override
//    public void refresh() {
//        super.refresh();
//
//        loadData(1);
//
//    }
//
//    @Override
//    public void loadMore() {
//        super.loadMore();
//        loadData(getPage());
//    }
//
//
    @SuppressLint("StaticFieldLeak")
    private void loadData(int page) {
        new HttpTask<ContentData>(new WCallback<List<ContentData>>() {
            @Override
            public void onSuccess(List<ContentData> list) {
                if (page == 1) {
                    setList(list);
                    showContent(true);
                } else {
                    addList(list);
                    getBinding().refresh.setEnableLoadMore(false);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                showError(s);
            }

            @Override
            public void onError(Throwable throwable) {
                showError(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                loadComplete();
            }
        }) {
            @Override
            public ContentData createData(int i) {
                return new ContentData(getRandomColor(), "ContentData:" + i);
            }
        }.execute(page);
    }

}
