package com.zhengdianfang.homework.homework.common;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by zheng on 2016/11/16.
 */

public class CommonMethod {

    public  interface ClickSpanListener{
        void onClick(View view);
    }

    public static void addClickSpan(SpannableStringBuilder spannableString, String matchStr, int color, ClickSpanListener listener){
        int start = spannableString.toString().indexOf(matchStr);
        int end = start  + matchStr.length();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(null != listener){
                    listener.onClick(widget);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }
}
