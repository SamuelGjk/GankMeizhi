package com.happy.samuelalva.gankmeizhi.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.happy.samuelalva.gankmeizhi.support.adapter.GankListAdapter;
import com.happy.samuelalva.gankmeizhi.support.adapter.base.BaseListAdapter;
import com.happy.samuelalva.gankmeizhi.support.network.GankMeizhiApi;
import com.happy.samuelalva.gankmeizhi.ui.fragment.base.BaseFragment;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by SamuelGjk on 2015/9/9.
 */
public class AndroidFragment extends BaseFragment {
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new GankListAdapter(getContext(), results);
    }

    @Override
    protected void scrolled(int dx, int dy) {
        if (dy > 0 && !mSwipeRefreshLayout.isRefreshing() && ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition() > mAdapter.getItemCount() - 3) {
            mSwipeRefreshLayout.setRefreshing(true);
            getData(false);
        }
    }

    @Override
    protected void getData(boolean clean) {
        Observable.zip(GankMeizhiApi.getInstance().getAndroidData(++curPage), GankMeizhiApi.getInstance().get随机福利(), (android, meizhi) -> {
            ((GankListAdapter) mAdapter).addBackground(meizhi.results);
            return android;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(android -> updateData(clean, android.results), throwable -> loadFailure());
    }
}
