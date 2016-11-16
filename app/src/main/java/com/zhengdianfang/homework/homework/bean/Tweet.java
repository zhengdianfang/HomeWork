package com.zhengdianfang.homework.homework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by zheng on 2016/11/16.
 * tweet model
 */

public class Tweet implements Parcelable{

    public String content;
    public ArrayList<TweetImage> tweetImages;
    public User sender;
    @JsonProperty("comments")
    public ArrayList<TweetComment> tweetComments;

    protected Tweet(Parcel in) {
        content = in.readString();
        tweetImages = in.createTypedArrayList(TweetImage.CREATOR);
        sender = in.readParcelable(User.class.getClassLoader());
        tweetComments = in.createTypedArrayList(TweetComment.CREATOR);
    }

    public Tweet() {
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeTypedList(tweetImages);
        dest.writeParcelable(sender, flags);
        dest.writeTypedList(tweetComments);
    }
}
