package com.zhengdianfang.homework.homework.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.presenter.view.TweetListPresenter;
import com.zhengdianfang.homework.homework.presenter.view.TweetsView;
import com.zhengdianfang.homework.homework.ui.adapter.TweetItemAdapter;

import java.util.List;

public class MainActivity extends BaseActivity implements TweetsView {

    private TweetListPresenter mTweetListPresenter;
    private TweetItemAdapter mTweetItemAdapter;
    private ProgressBar mLoadingBar;
    private RecyclerView mTweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        mTweetItemAdapter = new TweetItemAdapter(this);
        mTweetList = (RecyclerView) findViewById(R.id.tweetList);
        mTweetList.setAdapter(mTweetItemAdapter);
        mLoadingBar = (ProgressBar) findViewById(R.id.loadingBar);


        //load datas
        mTweetListPresenter = new TweetListPresenter(this);
        mTweetListPresenter.onCreate();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTweetListPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTweetListPresenter.onRestoreState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTweetListPresenter.onDestory();
        mTweetItemAdapter.destroy();
    }

    @Override
    public void onShowUserInfor(User user) {
        mTweetItemAdapter.setUser(user);
    }

    @Override
    public void onShowTweetList(List<Tweet> tweets) {
        mTweetItemAdapter.setTweetList(tweets);
    }

    @Override
    public void showProgress() {
        mLoadingBar.setVisibility(View.VISIBLE);
        mTweetList.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mLoadingBar.setVisibility(View.GONE);
        mTweetList.setVisibility(View.VISIBLE);
    }
}
