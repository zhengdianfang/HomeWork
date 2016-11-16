package com.zhengdianfang.homework.homework.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zheng on 2016/11/16.
 *  comment model .
 */

public class TweetComment implements Parcelable {

    public String content;
    public User sender;

    public TweetComment() {
    }

    protected TweetComment(Parcel in) {
        content = in.readString();
        sender = in.readParcelable(User.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeParcelable(sender, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TweetComment> CREATOR = new Creator<TweetComment>() {
        @Override
        public TweetComment createFromParcel(Parcel in) {
            return new TweetComment(in);
        }

        @Override
        public TweetComment[] newArray(int size) {
            return new TweetComment[size];
        }
    };
}
