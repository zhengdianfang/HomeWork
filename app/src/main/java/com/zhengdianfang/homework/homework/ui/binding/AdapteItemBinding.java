package com.zhengdianfang.homework.homework.ui.binding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by zheng on 2016/11/16.
 */

public abstract class AdapteItemBinding<T> {

    protected Context mContext;

    public AdapteItemBinding(@NonNull Context context) {
        mContext = context;
    }



    public abstract void bind(View rootView, T t, int posistion);

    public  void destory(){
        mContext = null;
    }

    public abstract View getView(ViewGroup parent);
}
