package com.happy.samuelalva.gankmeizhi.support.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.happy.samuelalva.gankmeizhi.model.Result;

import java.util.List;

/**
 * Created by SamuelGjk on 2015/9/9.
 */
public class BaseListAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    protected Context mContext;
    protected List<Result> mList;

    public BaseListAdapter(Context context, List<Result> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
