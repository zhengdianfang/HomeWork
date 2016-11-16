package com.zhengdianfang.homework.homework.presenter.view;

import android.content.Context;

/**
 * Created by zheng on 2016/11/16.
 * ui callback
 */

public interface IView {

    Context getContext();

    void toastErrorMsg(String msg);

    void showProgress();
    void hideProgress();

    void showDialog(String content);
    void dimissDialog();
}
