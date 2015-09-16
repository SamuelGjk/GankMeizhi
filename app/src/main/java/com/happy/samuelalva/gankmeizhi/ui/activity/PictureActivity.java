package com.happy.samuelalva.gankmeizhi.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.happy.samuelalva.gankmeizhi.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by SamuelGjk on 2015/9/9.
 */
public class PictureActivity extends AppCompatActivity {
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String MEIZHI_PIC = "MEIZHI_PIC";

    @Bind(R.id.picture) ImageView picture;
    @BindColor(R.color.picture_activity_bg) int statusBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(statusBarColor);

        ViewCompat.setTransitionName(picture, MEIZHI_PIC);

        Picasso.with(this).load(getIntent().getStringExtra(IMAGE_URL)).into(picture);

        PhotoViewAttacher mPhotoViewAttacher = new PhotoViewAttacher(picture);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> supportFinishAfterTransition());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
