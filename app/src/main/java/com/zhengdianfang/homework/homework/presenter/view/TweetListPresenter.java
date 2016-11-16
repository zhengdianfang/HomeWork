package com.zhengdianfang.homework.homework.presenter.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.common.Constans;
import com.zhengdianfang.homework.homework.net.interactor.TweetInteractor;
import com.zhengdianfang.homework.homework.net.interactor.UserInteractor;
import com.zhengdianfang.homework.homework.presenter.BaseListPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zheng on 2016/11/16.
 * the Tweet List Page Presenter.
 */

public class TweetListPresenter extends BaseListPresenter<TweetsView, Pair<User, List<Tweet>>> {

    private static final String CACHE_TWEET_LIST = "cache_tweet_list";
    private static final String CACHE_USER = "cache_user";

    private ArrayList<Tweet> mTweets = new ArrayList<>();
    private User mUser;
    private UserInteractor mUserInteractor = new UserInteractor();
    private TweetInteractor mTweetInteractor = new TweetInteractor();
    private int pageIndex = 0;
    private int pageNum = 5;

    public TweetListPresenter(TweetsView IView) {
        super(IView);
    }

    @Override
    public void refreshRequest() {
        pageIndex = 0;
        Subscription subscribe = Observable.zip(mUserInteractor.getUserInforObervable(Constans.DEFAULT_USER_NAME)
                , mTweetInteractor.getTweetListObervable(Constans.DEFAULT_USER_NAME), (user, tweets) -> Pair.create(user, tweets))
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(userListPair -> {
                    onFinished(userListPair);
                }, throwable -> {
                    onError(throwable.getMessage());
                });

        mCompositeSubscription.add(subscribe);
    }

    @Override
    public void loadMoreRequest() {
        ++pageIndex;
        mIView.onShowTweetList(mTweets.subList(pageIndex, pageIndex + pageNum));
    }

    @Override
    public void onFinished(Pair<User, List<Tweet>> result) {
        if (null != mIView) {
            if (null != result.first){
                mUser = result.first;
                mIView.onShowUserInfor(result.first);
            }
            if (result.second != null) {
                mTweets.clear();
                mTweets.addAll(result.second);
            }
            mIView.onShowTweetList(mTweets);

        }
        super.onFinished(result);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (null != mTweets && !mTweets.isEmpty()){
            outState.putParcelableArrayList(CACHE_TWEET_LIST, mTweets);
        }
        if (null != mUser){
            outState.putParcelable(CACHE_USER, mUser);
        }
    }

    @Override
    public void onRestoreState(@NonNull Bundle savedInstanceState) {
        mTweets = savedInstanceState.getParcelableArrayList(CACHE_TWEET_LIST);
        mUser = savedInstanceState.getParcelable(CACHE_USER);
    }

}
