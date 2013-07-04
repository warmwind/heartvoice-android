package com.thoughtworks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainView extends LinearLayout {
    EditText editText;
    Button talkButton, readButton;

    public MainView(Context context) {
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
                new SynthesizerDialogInitializer(getContext(), editText).show();
            }
        });
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
