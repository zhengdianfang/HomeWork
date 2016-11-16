package com.zhengdianfang.homework.homework.net.interactor;

import com.zhengdianfang.homework.homework.bean.User;

import org.junit.After;
import org.junit.Test;

import rx.android.plugins.RxAndroidPlugins;

/**
 * Created by zheng on 2016/11/16.
 */
public class UserInteractorTest extends BaseInteractorTest{



    @Test
    public void getUserInforRequest() throws Exception {
        UserInteractor userInteractor = new UserInteractor();
        userInteractor.getUserInforRequest(new BaseInteractor.OnFinishedListener<User>() {
            @Override
            public void onFinished(User result) {
                System.out.println(result.nick);
                System.out.println(result.profileImage);
                System.out.println(result.username);
                System.out.println(result.avatar);
            }

            @Override
            public void onError(String errorMsg) {
                System.out.println(errorMsg);
            }
        });

        Thread.sleep(10*1000);

    }

}