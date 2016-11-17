package com.zhengdianfang.homework.homework.ui.binding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.User;

/**
 * Created by zheng on 2016/11/16.
 * user profile
 */

public class AdapterUserProfileHeaderViewBinding extends AdapteItemBinding<User> {

    public AdapterUserProfileHeaderViewBinding(@NonNull Context context) {
        super(context);
    }

    @Override
    public void bind(View rootView, User user, int posistion) {
        ImageView profileImageView = (ImageView) rootView.findViewById(R.id.profileImageView);
        ImageView avatarImageView = (ImageView) rootView.findViewById(R.id.avatarImageView);
        TextView nickNameTextView = (TextView) rootView.findViewById(R.id.nickNameTextView);
        if (user != null) {
            Glide.with(mContext).load(user.profileImage).placeholder(R.drawable.tweet_profile_image_placehoder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().priority(Priority.HIGH).into(profileImageView);
            Glide.with(mContext).load(user.avatar).placeholder(R.drawable.ic_avatar_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().into(avatarImageView);
            nickNameTextView.setText(Html.fromHtml(user.nick));//名字可能存在表情符号，所以用Html解析。
        }
    }

    @Override
    public View getView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tweet_user_profile_header_item_layout, parent, false);
    }


}
