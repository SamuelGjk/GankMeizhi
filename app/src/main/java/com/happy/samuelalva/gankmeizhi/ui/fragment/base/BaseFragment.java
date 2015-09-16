package com.happy.samuelalva.gankmeizhi.ui.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.samuelalva.gankmeizhi.R;
import com.happy.samuelalva.gankmeizhi.model.Result;
import com.happy.samuelalva.gankmeizhi.support.adapter.base.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SamuelGjk on 2015/9/9.
 */
public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = BaseFragment.class.getSimpleName();

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseListAdapter mAdapter;
    protected List<Result> results;
    protected int curPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_item, container, false);
        ButterKnife.bind(this, view);
        results = new ArrayList<>();
        initSwipeRefreshLayout();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(() -> refresh(), 358);
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager = getLayoutManager());
        mRecyclerView.setAdapter(mAdapter = getAdapter());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrolled(dx, dy);
            }
        });
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract BaseListAdapter getAdapter();

    protected abstract void scrolled(int dx, int dy);

    protected abstract void getData(boolean clean);

    protected void updateData(boolean clean, List<Result> data) {
        if (clean) results.clear();
        results.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected void loadFailure() {
        mSwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(mSwipeRefreshLayout, getClass().getSimpleName() + " load failure", Snackbar.LENGTH_SHORT).show();
    }

    public void refresh() {
        if (mSwipeRefreshLayout.isRefreshing()) return;
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.smoothScrollToPosition(0);
        curPage = 0;
        getData(true);
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
