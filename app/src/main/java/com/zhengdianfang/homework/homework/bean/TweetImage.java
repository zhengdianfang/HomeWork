package com.zhengdianfang.homework.homework.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zheng on 2016/11/16.
 * tweet image model
 */

public class TweetImage implements Parcelable{
    public String url;

    protected TweetImage(Parcel in) {
        url = in.readString();
    }

    public TweetImage() {
    }

    public static final Creator<TweetImage> CREATOR = new Creator<TweetImage>() {
        @Override
        public TweetImage createFromParcel(Parcel in) {
            return new TweetImage(in);
        }

        @Override
        public TweetImage[] newArray(int size) {
            return new TweetImage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
