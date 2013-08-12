package com.thoughtworks;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainView extends LinearLayout {
    EditText editText;
    Button talkButton, readButton;

    public MainView(final Context context) {
        super(context);
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.main, this, true);
        editText = (EditText) findViewById(R.id.word);
        talkButton = (Button) findViewById(R.id.talk_button);
        readButton = (Button) findViewById(R.id.read_button);

        talkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new RecognizerDialogInitializer(getContext(), editText).show();
            }
        });
        readButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new SynthesizerDialogInitializer(context, editText).show();
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editText.setText(preferences.getString(SettingsActivity.KEY_DEFAULT_TEXT, ""));
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, final int h, int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        android.os.Handler handler = getHandler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (h > oldh && oldh > 0) {
                    editText.setTextSize(50);
                    findViewById(R.id.actions).setVisibility(VISIBLE);
                    findViewById(R.id.content_actions).setVisibility(VISIBLE);
                } else if (h < oldh) {
                    editText.setTextSize(20);
                    findViewById(R.id.actions).setVisibility(GONE);
                    findViewById(R.id.content_actions).setVisibility(GONE);
                }
            }
        });
    }

    public void clear() {
        editText.setText("");
    }
}
