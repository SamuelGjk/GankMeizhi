package com.happy.samuelalva.gankmeizhi.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SamuelGjk on 2015/9/8.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private int mDySinceDirectionChange;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        if (dy > 0 && mDySinceDirectionChange < 0 || dy < 0 && mDySinceDirectionChange > 0) {
            mDySinceDirectionChange = 0;
        }

        mDySinceDirectionChange += dy;

        if (mDySinceDirectionChange > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (mDySinceDirectionChange < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}
