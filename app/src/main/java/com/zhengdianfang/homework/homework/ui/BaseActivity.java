package com.zhengdianfang.homework.homework.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhengdianfang.homework.homework.presenter.view.IView;

/**
 * Created by zheng on 2016/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView{

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void toastErrorMsg(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog(String content) {

    }

    @Override
    public void dimissDialog() {

    }

    @Override
    public void onTrimMemory(int level) {
        Glide.get(this.getContext().getApplicationContext()).clearMemory();
    }

    public int dip(float dpValue){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int sp(float dpValue){
        float scale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * scale + 0.5f);
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void toast(int res){
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

}
