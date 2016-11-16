package com.zhengdianfang.homework.homework.presenter.view;

import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;

import java.util.List;

/**
 * Created by zheng on 2016/11/16.
 */

public interface TweetsView extends IView {

    /**
     * display user profile views.
     * @param user
     */
    void onShowUserInfor(User user);

    /**
     * display tweet listview.
     * @param tweets
     */
    void onShowTweetList(List<Tweet> tweets);
}
