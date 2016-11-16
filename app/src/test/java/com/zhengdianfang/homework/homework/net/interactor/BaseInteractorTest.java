package com.zhengdianfang.homework.homework.net.interactor;

import org.junit.After;
import org.junit.Before;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by zheng on 2016/11/16.
 */

public class BaseInteractorTest {


    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.io();
            }
        });
    }



    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

}
