package com.zhengdianfang.homework.homework.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.presenter.view.TweetListPresenter;
import com.zhengdianfang.homework.homework.presenter.view.TweetsView;
import com.zhengdianfang.homework.homework.ui.adapter.TweetItemAdapter;
import com.zhengdianfang.homework.homework.ui.views.HomeworkSwipeRefreshLayout;

import java.util.List;

public class MainActivity extends BaseActivity implements TweetsView, HomeworkSwipeRefreshLayout.OnRefreshListener{

    private TweetListPresenter mTweetListPresenter;
    private TweetItemAdapter mTweetItemAdapter;
    private ProgressBar mLoadingBar;
    private RecyclerView mTweetList;
    private HomeworkSwipeRefreshLayout mRefreshFrameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTweetItemAdapter = new TweetItemAdapter(this);
        mTweetList = (RecyclerView) findViewById(R.id.tweetList);
        mTweetList.setItemAnimator(null);
        mTweetList.setAdapter(mTweetItemAdapter);
        mTweetList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (layoutManager.getChildCount() > 0
                            && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount()) {
                        mTweetListPresenter.loadMoreRequest();
                    }
                }
            }
        });
        mLoadingBar = (ProgressBar) findViewById(R.id.loadingBar);

        mRefreshFrameView = (HomeworkSwipeRefreshLayout) findViewById(R.id.refreshFrameView);
        mRefreshFrameView.setOnRefreshListener(this);

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
        mRefreshFrameView.setRefreshing(false);
    }

    @Override
    public void onShowTweetList(List<Tweet> tweets, boolean isAll, boolean isLoadmore) {
        mTweetItemAdapter.setCanLoadmore(isAll);
        mTweetItemAdapter.setTweetList(tweets, isLoadmore);
        mRefreshFrameView.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        mTweetListPresenter.refreshRequest();
    }
}
