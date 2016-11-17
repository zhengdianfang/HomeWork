package com.zhengdianfang.homework.homework.ui.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhengdianfang.homework.homework.R;
import com.zhengdianfang.homework.homework.bean.TweetImage;

import java.util.List;

/**
 * Created by zheng on 2016/11/16.
 */

public class TweetNineGridLayout extends NineGridLayout {


    public TweetNineGridLayout(Context context) {
        super(context);
    }

    public TweetNineGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(ImageView imageView, String url, int parentWidth) {
        imageView.setImageResource(R.drawable.nice_grid_image_placeholder);
        Glide.with(getContext()).load(url).asBitmap().into(new BitmapImageViewTarget(imageView){

            @Override
            public void onLoadStarted(Drawable placeholder) {
                imageView.setImageResource(R.drawable.nice_grid_image_placeholder);
            }

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    int w = resource.getWidth();
                    int h = resource.getHeight();

                    int newWidth;
                    int newHeight;
                    if (h > w * 3) {//h:w = 5:3
                        newWidth = parentWidth / 2;
                        newHeight = newWidth * 5 / 3;
                    } else if (h < w) {//h:w = 2:3
                        newWidth = parentWidth * 2 / 3;
                        newHeight = newWidth * 2 / 3;
                    } else {//newH:h = newW :w
                        newWidth = parentWidth / 2;
                        newHeight = h * newWidth / w;
                    }
                    LayoutParams layoutParams = imageView.getLayoutParams();
                    layoutParams.width = newWidth;
                    layoutParams.height = newHeight;
                    imageView.setImageBitmap(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                imageView.setImageResource(R.drawable.tweet_image_error_drawable);

            }
        });
        return false;
    }

    @Override
    protected void displayImage(ImageView imageView, String url) {
        Glide.with(getContext()).load(url).placeholder(R.drawable.nice_grid_image_placeholder)
                .error(R.drawable.tweet_image_error_drawable).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    @Override
    protected void onClickImage(View view, int position, String url, List<TweetImage> urlList) {
        //click image event.
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            GenericRequest genericRequest = (GenericRequest) imageView.getTag();
            if (genericRequest.isFailed()) {
                genericRequest.begin();
                Toast.makeText(mContext, R.string.tweet_image_request_retry_toast, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
