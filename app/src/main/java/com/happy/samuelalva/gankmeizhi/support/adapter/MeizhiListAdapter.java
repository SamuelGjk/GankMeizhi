package com.happy.samuelalva.gankmeizhi.support.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.samuelalva.gankmeizhi.R;
import com.happy.samuelalva.gankmeizhi.model.Result;
import com.happy.samuelalva.gankmeizhi.support.adapter.base.BaseListAdapter;
import com.happy.samuelalva.gankmeizhi.ui.activity.PictureActivity;
import com.happy.samuelalva.gankmeizhi.widget.RatioImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SamuelGjk on 2015/9/8.
 */
public class MeizhiListAdapter extends BaseListAdapter<MeizhiListAdapter.ViewHolder> {
    public static final String TAG = MeizhiListAdapter.class.getSimpleName();

    public MeizhiListAdapter(Context context, List<Result> list) {
        super(context, list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizhi_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = mList.get(position);
        holder.url = result.url;

        holder.meizhiView.setOriginalSize(result.width, result.height);
        Picasso.with(mContext).load(holder.url).config(Bitmap.Config.RGB_565).into(holder.meizhiView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_meizhi)
        RatioImageView meizhiView;
        String url;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view_meizhi)
        void onMeizhi() {
            Intent intent = new Intent(mContext, PictureActivity.class);
            intent.putExtra(PictureActivity.IMAGE_URL, url);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, meizhiView, PictureActivity.MEIZHI_PIC);
            ActivityCompat.startActivity((Activity) mContext, intent, optionsCompat.toBundle());
        }
    }
}
