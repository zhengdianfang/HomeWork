package com.zhengdianfang.homework.homework.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zhengdianfang.homework.homework.presenter.view.IView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by zheng on 2016/11/16.
 */

public abstract class BasePresenter<T extends IView> {

    protected @Nullable T mIView;
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public BasePresenter(T IView) {
        mIView = IView;
    }


    /**
     * Should be called by the view when the state of a previous view restores
     *
     * @param savedInstanceState the saved state
     */
    public void onRestoreState(@NonNull Bundle savedInstanceState){

    }

    public void onCreate(){

    }

    public void onDestory() {
        mIView = null;
        mCompositeSubscription.clear();
    }

    /**
     * Should be called by the view before being destroyed to save its current state
     *
     * @param outState bundle to put data into
     */
    public void onSaveInstanceState(@NonNull Bundle outState){

    }
}
