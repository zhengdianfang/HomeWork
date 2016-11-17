package com.zhengdianfang.homework.homework.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.ui.binding.AdapterTweetItemViewBinding;
import com.zhengdianfang.homework.homework.ui.binding.AdapterUserProfileHeaderViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng on 2016/11/16.
 */

public class TweetItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADE_TYPE = 0;
    public static final int ITEM_TYPE = 1;
    public static final int MORE_TYPE = 2;

    private User mUser;
    private List<Tweet> mTweetList = new ArrayList<>();
    private AdapterTweetItemViewBinding mAdapterTweetItemViewBinding;
    private AdapterUserProfileHeaderViewBinding mAdapterUserProfileHeaderViewBinding;
    private boolean canLoadmore = false;
    private View mFooterView;
    private int mFooterViewHeight = 0;

    public TweetItemAdapter(Context context) {
        mAdapterTweetItemViewBinding = new AdapterTweetItemViewBinding(context);
        mAdapterUserProfileHeaderViewBinding = new AdapterUserProfileHeaderViewBinding(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADE_TYPE) {
            return new RecyclerView.ViewHolder(mAdapterUserProfileHeaderViewBinding.getView(parent)) {};
        }else if (viewType == MORE_TYPE) {
            mFooterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loadmore_foote_item_layout, parent, false);
            mFooterViewHeight =  mFooterView.getLayoutParams().height;
            return new RecyclerView.ViewHolder(mFooterView) {};
        }
        return new RecyclerView.ViewHolder(mAdapterTweetItemViewBinding.getView(parent)) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == HEADE_TYPE && null != mUser) {
            mAdapterUserProfileHeaderViewBinding.bind(holder.itemView, mUser, position);
        }else if (itemViewType == ITEM_TYPE && null != mTweetList && position - 1 < mTweetList.size()){
            int realPosition = position - ITEM_TYPE;
            Tweet tweet = mTweetList.get(realPosition);
            mAdapterTweetItemViewBinding.bind(holder.itemView, tweet, realPosition);
        }
    }

    @Override
    public int getItemCount() {
        return (mTweetList == null ? 0 : mTweetList.size()) + 2;
    }

    public void setCanLoadmore(boolean canLoadmore) {
        this.canLoadmore = canLoadmore;
        if (canLoadmore){
            mFooterView.getLayoutParams().height = mFooterViewHeight;
        }else {
            mFooterView.getLayoutParams().height = 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADE_TYPE;
        }
        if (position == getItemCount() - 1){
            return MORE_TYPE;
        }
        return ITEM_TYPE;
    }

    public void destroy(){
        mAdapterUserProfileHeaderViewBinding.destory();
        mAdapterTweetItemViewBinding.destory();
        mUser = null;
        mTweetList.clear();
        mTweetList = null;
    }

    public void setUser(User user) {
        mUser = user;
        notifyDataSetChanged();
    }

    public void setTweetList(List<Tweet> tweetList, boolean isLoadmore) {
        if (!isLoadmore){
            mTweetList.clear();
        }
        mTweetList.addAll(tweetList);
        notifyDataSetChanged();
    }
}
