package com.zhengdianfang.homework.homework.ui.binding;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.bean.TweetComment;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.common.CommonMethod;
import com.zhengdianfang.homework.homework.ui.views.TweetNineGridLayout;

/**
 * Created by zheng on 2016/11/16.
 */

public class AdapterTweetItemViewBinding extends AdapteItemBinding<Tweet> {

    public AdapterTweetItemViewBinding(@NonNull Context context) {
        super(context);
    }

    @Override
    public void bind(View rootView, Tweet tweet, int posistion) {
        ImageView tweetSenderAvatarImageView = (ImageView) rootView.findViewById(R.id.tweetSenderAvatarImageView);
        TextView tweetSenderNicknameTextView = (TextView) rootView.findViewById(R.id.tweetSenderNicknameTextView);
        TextView tweetContentTextView = (TextView) rootView.findViewById(R.id.tweetContentTextView);
        TweetNineGridLayout tweetImagesFrameView = (TweetNineGridLayout) rootView.findViewById(R.id.tweetImagesFrameView);
        LinearLayout tweetCommentsFrameView = (LinearLayout) rootView.findViewById(R.id.tweetCommentsFrameView);
        if (tweet != null) {
            //init sender profile.
            User sender = tweet.sender;
            if (sender != null) {
                Glide.with(mContext).load(sender.avatar).centerCrop().placeholder(R.drawable.ic_avatar_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(tweetSenderAvatarImageView);
                tweetSenderNicknameTextView.setText(sender.nick);
            }
            //init content
            if (TextUtils.isEmpty(tweet.content)) {
                tweetContentTextView.setVisibility(View.GONE);
            }else {
                tweetContentTextView.setVisibility(View.VISIBLE);
                tweetContentTextView.setText(Html.fromHtml(tweet.content));
            }
            //init images frame.
            tweetImagesFrameView.setSpacing(2);
            tweetImagesFrameView.setUrlList(tweet.tweetImages);
            //init comments frame.
            initCommentsViews(tweet, tweetCommentsFrameView);
        }

    }

    private void initCommentsViews(Tweet tweet, LinearLayout tweetCommentsFrameView) {
        if (null != tweet.tweetComments && !tweet.tweetComments.isEmpty()) {
            tweetCommentsFrameView.removeAllViews();
            for (TweetComment tweetComment : tweet.tweetComments) {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                String content = tweetComment.content;
                String nick = "";
                if (null != tweetComment.sender) {
                    nick = tweetComment.sender.nick;
                    builder.append(nick).append(": ");
                }
                builder.append(content);
                String finalNick = nick;
                CommonMethod.addClickSpan(builder, nick + ": ", ContextCompat.getColor(mContext, R.color.c_576b95), view -> {
                    // click sender nickname.
                    Toast.makeText(mContext, mContext.getString(R.string.click_comment_sender_toast, finalNick), Toast.LENGTH_SHORT).show();
                });
                TextView textView = new TextView(mContext);
                textView.setMovementMethod(new LinkMovementMethod());
                textView.setText(builder);
                textView.setTextColor(Color.BLACK);
                tweetCommentsFrameView.addView(textView);
            }
            tweetCommentsFrameView.setVisibility(View.VISIBLE);
        }else {
            tweetCommentsFrameView.setVisibility(View.GONE);
        }
    }

    @Override
    public View getView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tweet_item_layout, parent, false);
    }
}
