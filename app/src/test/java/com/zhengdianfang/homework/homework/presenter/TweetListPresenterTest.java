package com.zhengdianfang.homework.homework.presenter;

import com.zhengdianfang.homework.homework.presenter.view.TweetListPresenter;
import com.zhengdianfang.homework.homework.presenter.view.TweetsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by zheng on 2016/11/17.
 */
public class TweetListPresenterTest {


    private TweetListPresenter mTweetListPresenter;

    @Mock
    private TweetsView mTweetsView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(mTweetsView != null).thenReturn(true);

        mTweetListPresenter = new TweetListPresenter(mTweetsView);
    }

    @Test
    public void refreshRequest() throws Exception {
        mTweetListPresenter.loadMoreRequest();
        verify(mTweetsView).showProgress();
       // verify(mTweetsView).onShowUserInfor();
    }

    @Test
    public void loadMoreRequest() throws Exception {

    }

    @Test
    public void onFinished() throws Exception {

    }

}