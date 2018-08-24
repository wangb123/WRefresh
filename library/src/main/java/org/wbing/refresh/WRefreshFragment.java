package org.wbing.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import org.wbing.base.ui.impl.WFrag;
import org.wbing.refresh.databinding.WRefreshBinding;

import java.util.Objects;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public abstract class WRefreshFragment extends WFrag<WRefreshBinding> {

    @Override
    public final int layoutId() {
        return R.layout.w_refresh;
    }

    @Override
    public void onPostViewCreate() {
        super.onPostViewCreate();
        //初始化布局文件
        if (!Objects.requireNonNull(getBinding()).contain.isInflated()) {
            Objects.requireNonNull(getBinding().contain.getViewStub()).setLayoutResource(containId());
            getBinding().contain.setOnInflateListener((stub, inflated) -> initContainView(inflated));
            getBinding().contain.getViewStub().inflate();
        }
        //设置是否可以刷新
        getBinding().refresh.setEnableRefresh(enableRefresh());
        //设置是否可以加载更多
        getBinding().refresh.setEnableLoadMore(enableLoadMore());

        //设置刷新回调
        getBinding().refresh.setOnRefreshListener(refreshLayout -> refresh());
        getBinding().content.setRetryListener(v -> {
            refresh();
            showLoading();
        });

        //设置加载更多回调
        getBinding().refresh.setOnLoadMoreListener(refreshLayout -> loadMore());

    }


    abstract boolean enableRefresh();

    abstract boolean enableLoadMore();

    abstract int containId();

    public void initContainView(View containView) {
    }

    public void refresh() {
    }

    public void loadMore() {
    }


    @Override
    public void loadData() {
        if (enableRefresh()) {
            Objects.requireNonNull(getBinding()).refresh.autoRefresh(400);
        } else {
            refresh();
        }
    }

    public void loadComplete() {
        switch (Objects.requireNonNull(getBinding()).refresh.getState()) {
            case Loading:
                getBinding().refresh.finishLoadMore();
                break;
            case Refreshing:
                getBinding().refresh.finishRefresh();
                break;
        }
    }

    public void showContent() {
        //设置是否可以刷新
        Objects.requireNonNull(getBinding()).refresh.setEnableRefresh(enableRefresh());
        //设置是否可以加载更多
        getBinding().refresh.setEnableLoadMore(enableLoadMore());
//        getBinding().refresh.setRefreshContent(getBinding().contain.getRoot());
        getBinding().content.showContent();
    }

    public void showEmpty() {
        //设置是否可以刷新
        Objects.requireNonNull(getBinding()).refresh.setEnableRefresh(false);
        //设置是否可以加载更多
        getBinding().refresh.setEnableLoadMore(false);
//        getBinding().refresh.setRefreshContent(getBinding().content.showEmpty());
        getBinding().content.showEmpty();
    }

    public void showError() {
        //设置是否可以刷新
        Objects.requireNonNull(getBinding()).refresh.setEnableRefresh(false);
        //设置是否可以加载更多
        getBinding().refresh.setEnableLoadMore(false);
//        getBinding().refresh.setRefreshContent(getBinding().content.showError());
        getBinding().content.showError();
    }

    public void showLoading() {
        //设置是否可以刷新
        Objects.requireNonNull(getBinding()).refresh.setEnableRefresh(false);
        //设置是否可以加载更多
        getBinding().refresh.setEnableLoadMore(false);
        getBinding().refresh.setEnableLoadMore(enableLoadMore());
//        getBinding().refresh.setRefreshContent(getBinding().content.showLoading());
        getBinding().content.showLoading();
    }
}
