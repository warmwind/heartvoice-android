package com.HeartVoice;

import android.app.Activity;
import android.os.Bundle;

public class AboutUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AboutUsView(this));
    }
}
