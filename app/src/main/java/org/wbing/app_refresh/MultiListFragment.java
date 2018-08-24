package org.wbing.app_refresh;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import org.wbing.app_refresh.data.AdData;
import org.wbing.app_refresh.data.ContentData;
import org.wbing.app_refresh.data.NewsData;
import org.wbing.base.ui.WCallback;
import org.wbing.refresh.WRefreshMultiListFragment;
import org.wbing.view.list.IMultiItem;
import org.wbing.view.list.LineItemDecoration;
import org.wbing.view.list.WRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class MultiListFragment extends WRefreshMultiListFragment<IMultiItem> {
    @Override
    protected int variableId() {
        return BR.data;
    }

    @Override
    public void initListView(WRecyclerView list) {
        super.initListView(list);
        list.addItemDecoration(new LineItemDecoration());
    }

    @Override
    public void refresh() {
        super.refresh();

        loadData(1);

    }

    @Override
    public void loadMore() {
        super.loadMore();
        loadData(getPage());
    }


    @SuppressLint("StaticFieldLeak")
    private void loadData(int page) {
        new HttpTask<IMultiItem>(new WCallback<List<IMultiItem>>() {
            @Override
            public void onSuccess(List<IMultiItem> list) {
                if (page == 1) {
                    setList(list);
                } else {
                    addList(list);
                }
                setNoMoreData(page == 5);
            }

            @Override
            public void onFailure(int i, String s) {
                if (page == 1) {
                    if (!TextUtils.isEmpty(s))
                        getBinding().content.setErrorText(s);
                    adapter().setList(null);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (page == 1) {
                    getBinding().content.setErrorText(throwable.getMessage());
                    adapter().setList(null);
                }
            }

            @Override
            public void onComplete() {
                loadComplete();
            }
        }) {
            @Override
            public IMultiItem createData(int i) {
                int random = (int) Math.round(Math.random() * 3);
                switch (random) {
                    case 1:
                        return new AdData(getRandomColor(), "广告：" + i);
                    case 2:
                        return new NewsData(getRandomColor(), "新闻：" + i);
                    default:
                        return new ContentData(getRandomColor(), "内容：" + i);
                }
            }
        }.execute(page);
    }
}