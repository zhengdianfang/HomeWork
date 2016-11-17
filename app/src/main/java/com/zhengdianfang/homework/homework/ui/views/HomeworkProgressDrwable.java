package com.zhengdianfang.homework.homework.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by zheng on 2016/11/17.
 */

public class HomeworkProgressDrwable extends MaterialProgressDrawable {
    private float rotation;
    private Drawable mDrawable;

    HomeworkProgressDrwable(Context context, View parent) {
        super(context, parent);
    }



    @Override
    public void setProgressRotation(float rotation) {
        this.rotation = -rotation*5*360;
        invalidateSelf();
    }

    @Override
    public void draw(Canvas c) {
        final int saveCount = c.save();
        Rect bounds = getBounds();
        c.rotate(rotation, bounds.exactCenterX(), bounds.exactCenterY());
        Logger.t(HomeworkProgressDrwable.class.getSimpleName()).d("rotation : " + rotation);
        mDrawable.setBounds(bounds);
        mDrawable.draw(c);
        c.restoreToCount(saveCount);
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}
