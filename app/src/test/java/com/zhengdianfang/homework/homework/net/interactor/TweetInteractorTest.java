package com.zhengdianfang.homework.homework.net.interactor;

import org.junit.Test;

/**
 * Created by zheng on 2016/11/16.
 */
public class TweetInteractorTest extends BaseInteractorTest{

    @Test
    public void getTweetListRequest() throws Exception {
        TweetInteractor tweetInteractor = new TweetInteractor();
        tweetInteractor.getTweetListObervable("jsmith")
            .subscribe(result -> {
                System.out.println("Tweet count : " + result.size());
                System.out.println("Tweet first content : "  + result.get(0).content);
                System.out.println("Tweet first sender nickname : "  +  result.get(0).sender.nick);
                System.out.println("Tweet first comments count : "  + result.get(0).tweetComments.size());
                System.out.println("Tweet first comment no1 content : "  + result.get(0).tweetComments.get(0).content);
                System.out.println("Tweet first comment no1 sender nickname : "  + result.get(0).tweetComments.get(0).sender.nick);
            }, throwable -> {
                System.out.println(throwable.getMessage());
            });

        Thread.sleep(10*1000);
    }

}