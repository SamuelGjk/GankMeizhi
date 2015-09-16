package com.happy.samuelalva.gankmeizhi.ui.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.happy.samuelalva.gankmeizhi.model.Result;
import com.happy.samuelalva.gankmeizhi.support.adapter.MeizhiListAdapter;
import com.happy.samuelalva.gankmeizhi.support.adapter.base.BaseListAdapter;
import com.happy.samuelalva.gankmeizhi.support.network.GankMeizhiApi;
import com.happy.samuelalva.gankmeizhi.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by SamuelGjk on 2015/9/8.
 */
public class MeizhiFragment extends BaseFragment {
    public static final String TAG = MeizhiFragment.class.getSimpleName();

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected BaseListAdapter getAdapter() {
        return new MeizhiListAdapter(getContext(), results);
    }

    @Override
    protected void scrolled(int dx, int dy) {
        if (dy > 0 && !mSwipeRefreshLayout.isRefreshing() && ((StaggeredGridLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPositions(new int[2])[1] > mAdapter.getItemCount() - 3) {
            mSwipeRefreshLayout.setRefreshing(true);
            getData(false);
        }
    }

    @Override
    protected void getData(boolean clean) {
        GankMeizhiApi.getInstance().get福利Data(++curPage)
                .map(meizhiData -> meizhiData.results)
                .flatMap(Observable::from)
                .map(this::getImageSize)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meizhis -> updateData(clean, meizhis), throwable -> loadFailure());
    }

    private Result getImageSize(Result meizhi) {
        try {
            Bitmap bitmap = Picasso.with(getContext()).load(meizhi.url).get();
            meizhi.width = bitmap.getWidth();
            meizhi.height = bitmap.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meizhi;
    }
}
