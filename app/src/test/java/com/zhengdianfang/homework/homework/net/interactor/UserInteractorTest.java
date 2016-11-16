package com.zhengdianfang.homework.homework.net.interactor;

import org.junit.Test;

/**
 * Created by zheng on 2016/11/16.
 */
public class UserInteractorTest extends BaseInteractorTest{



    @Test
    public void getUserInforRequest() throws Exception {
        UserInteractor userInteractor = new UserInteractor();
        userInteractor.getUserInforObervable("jsmith")
            .subscribe(result -> {
                System.out.println(result.nick);
                System.out.println(result.profileImage);
                System.out.println(result.username);
                System.out.println(result.avatar);
            }, throwable -> {
                System.out.println(throwable.getMessage());
            });

        Thread.sleep(10*1000);

    }

}