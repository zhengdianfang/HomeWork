package com.zhengdianfang.homework.homework.presenter;

import com.zhengdianfang.homework.homework.presenter.view.IView;

/**
 * Created by zheng on 2016/11/16.
 */

public abstract class BaseListPresenter<T extends IView, R> extends BasePresenter<T> {

    protected boolean isLoademore = false;

    public BaseListPresenter(T t) {
        super(t);
    }

    /**
     * pull request.
     */
    abstract public void refreshRequest();

    /**
     * load more request.
     */
    abstract public void loadMoreRequest();


    @Override
    public void onCreate() {
        if (mIView != null) {
            mIView.showProgress();
        }
        refreshRequest();
    }

    protected void onFinished(R result) {
        if (mIView != null) {
            mIView.hideProgress();
        }
    }

    protected void onError(String errorMsg) {
        if (mIView != null) {
            mIView.hideProgress();
            mIView.toastErrorMsg(errorMsg);
        }
    }

}