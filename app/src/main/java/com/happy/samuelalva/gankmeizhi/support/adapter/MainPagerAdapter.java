package com.happy.samuelalva.gankmeizhi.support.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.happy.samuelalva.gankmeizhi.ui.fragment.AndroidFragment;
import com.happy.samuelalva.gankmeizhi.ui.fragment.MeizhiFragment;

/**
 * Created by SamuelGjk on 2015/9/8.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public static final String[] TITLES = new String[]{"妹纸", "Android"};

    private SparseArray<Fragment> mPages;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mPages = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position) {
            case 0:
                f = new MeizhiFragment();
                break;
            case 1:
                f = new AndroidFragment();
                break;
        }
        mPages.append(position, f);
        return f;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    public Fragment getItemAt(int position) {
        return mPages.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (0 <= mPages.indexOfKey(position)) {
            mPages.remove(position);
        }
        super.destroyItem(container, position, object);
    }
}
