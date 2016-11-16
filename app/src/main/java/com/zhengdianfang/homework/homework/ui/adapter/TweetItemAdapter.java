package com.zhengdianfang.homework.homework.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.ui.binding.AdapterTweetItemViewBinding;
import com.zhengdianfang.homework.homework.ui.binding.AdapterUserProfileHeaderViewBinding;

import java.util.List;

/**
 * Created by zheng on 2016/11/16.
 */

public class TweetItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADE_TYPE = 0;
    public static final int ITEM_TYPE = 1;

    private User mUser;
    private List<Tweet> mTweetList;
    private AdapterTweetItemViewBinding mAdapterTweetItemViewBinding;
    private AdapterUserProfileHeaderViewBinding mAdapterUserProfileHeaderViewBinding;

    public TweetItemAdapter(Context context) {
        mAdapterTweetItemViewBinding = new AdapterTweetItemViewBinding(context);
        mAdapterUserProfileHeaderViewBinding = new AdapterUserProfileHeaderViewBinding(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADE_TYPE) {
            return new RecyclerView.ViewHolder(mAdapterUserProfileHeaderViewBinding.getView(parent)) {};
        }
        return new RecyclerView.ViewHolder(mAdapterTweetItemViewBinding.getView(parent)) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == HEADE_TYPE && null != mUser) {
            mAdapterUserProfileHeaderViewBinding.bind(holder.itemView, mUser, position);
        }else if (itemViewType == ITEM_TYPE && null != mTweetList && position < mTweetList.size()){
            int realPosition = position - ITEM_TYPE;
            Tweet tweet = mTweetList.get(realPosition);
            mAdapterTweetItemViewBinding.bind(holder.itemView, tweet, realPosition);
        }
    }

    @Override
    public int getItemCount() {
        return (mTweetList == null ? 0 : mTweetList.size()) + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADE_TYPE;
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

    public void setTweetList(List<Tweet> tweetList) {
        mTweetList = tweetList;
        notifyDataSetChanged();
    }
}
