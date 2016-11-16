package com.zhengdianfang.homework.homework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zheng on 2016/11/16.
 * user model.
 */

public class User implements Parcelable{

    @JsonProperty("profile-image")
    public String profileImage;
    public String avatar;
    public String nick;
    public String username;

    protected User(Parcel in) {
        profileImage = in.readString();
        avatar = in.readString();
        nick = in.readString();
        username = in.readString();
    }

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profileImage);
        dest.writeString(avatar);
        dest.writeString(nick);
        dest.writeString(username);
    }
}
