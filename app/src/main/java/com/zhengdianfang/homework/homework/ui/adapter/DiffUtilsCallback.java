package com.zhengdianfang.homework.homework.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by zheng on 16/10/8.
 */

public class DiffUtilsCallback<T> extends DiffUtil.Callback{

    public interface DiffUtilCompareListener<T>{
        boolean areItemsTheSame(T oldData, T newData);
        boolean areContentsTheSame(T oldData, T newData);
    }

    private List<T> newDatas, oldDatas;
    @NonNull
    private DiffUtilCompareListener mDiffUtilCompareListener;

    public DiffUtilsCallback(List<T> newDatas, List<T> oldDatas, @NonNull DiffUtilCompareListener diffUtilCompareListener) {
        this.newDatas = newDatas;
        this.oldDatas = oldDatas;
        mDiffUtilCompareListener = diffUtilCompareListener;
    }

    @Override
    public int getOldListSize() {
        return oldDatas == null ? 0 :oldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas == null ? 0 :newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mDiffUtilCompareListener.areItemsTheSame(oldDatas.get(oldItemPosition) ,newDatas.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mDiffUtilCompareListener.areContentsTheSame(oldDatas.get(oldItemPosition) ,newDatas.get(newItemPosition));
    }
}