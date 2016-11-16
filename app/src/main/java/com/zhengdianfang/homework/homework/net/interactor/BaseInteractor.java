package com.zhengdianfang.homework.homework.net.interactor;

/**
 * Created by zheng on 2016/11/16.
 *
 */

public interface BaseInteractor {

    interface OnFinishedListener<T>{
        void onFinished(T result);
        void onError(String errorMsg);
    }
}
