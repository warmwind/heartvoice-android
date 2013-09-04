package com.HeartVoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class AboutUsView extends LinearLayout {
    public AboutUsView(Context context) {
        super(context);
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.about_us, this, true);

    }
}
