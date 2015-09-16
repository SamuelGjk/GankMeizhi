package com.happy.samuelalva.gankmeizhi.support.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.samuelalva.gankmeizhi.R;
import com.happy.samuelalva.gankmeizhi.model.Result;
import com.happy.samuelalva.gankmeizhi.support.FastBlur;
import com.happy.samuelalva.gankmeizhi.support.adapter.base.BaseListAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SamuelGjk on 2015/9/9.
 */
public class GankListAdapter extends BaseListAdapter<GankListAdapter.ViewHolder> {
    public static final String TAG = GankListAdapter.class.getSimpleName();

    private List<Result> backgrounds;

    public GankListAdapter(Context context, List<Result> list) {
        super(context, list);
        backgrounds = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(backgrounds.get(position).url).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap bitmap = Bitmap.createScaledBitmap(source, source.getWidth() / 5, source.getHeight() / 5, false);
                source.recycle();
                Canvas canvas = new Canvas(bitmap);
                canvas.drawARGB(66, 144, 144, 144);

                return FastBlur.doBlur(bitmap, 2, true);
            }

            @Override
            public String key() {
                return "doBlur";
            }
        }).into(holder.background);

        Result gank = mList.get(position);
        holder.desc.setText(gank.desc);
        holder.whoAndDate.setText(new StringBuilder().append(gank.who).append("@").append(formatDate(gank.publishedAt)));
    }

    private String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public void addBackground(List<Result> bgs) {
        backgrounds.addAll(bgs);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_background)
        ImageView background;
        @Bind(R.id.tv_desc)
        TextView desc;
        @Bind(R.id.tv_who_and_date)
        TextView whoAndDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view_gank)
        void onGank() {

        }
    }
}
