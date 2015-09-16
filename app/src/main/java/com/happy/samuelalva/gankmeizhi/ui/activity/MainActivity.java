package com.happy.samuelalva.gankmeizhi.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.happy.samuelalva.gankmeizhi.R;
import com.happy.samuelalva.gankmeizhi.support.adapter.MainPagerAdapter;
import com.happy.samuelalva.gankmeizhi.ui.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mPager;

    MainPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPager.setAdapter(mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }

    @OnClick(R.id.fab)
    void onRefresh() {
        Fragment f = mPagerAdapter.getItemAt(mPager.getCurrentItem());
        if (f instanceof BaseFragment) {
            ((BaseFragment) f).refresh();
        }
    }
}
