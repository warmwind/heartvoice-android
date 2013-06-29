package com.thoughtworks;

import android.content.Context;
import android.widget.EditText;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;

public class SynthesizerDialogInitializer {
    private final Context context;
    private final EditText editText;

    public SynthesizerDialogInitializer(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;
    }

    public void show() {
        SynthesizerPlayerListener synbgListener = new SynthesizerPlayerListener() {
            public void onPlayBegin() {

            }

            public void onBufferPercent(int percent, int beginPos, int endPos) {

            }

            public void onPlayPaused() {

            }

            public void onPlayResumed() {

            }

            public void onPlayPercent(int percent, int beginPos, int endPos) {

            }

            public void onEnd(SpeechError error) {
            }
        };

        SynthesizerPlayer player = SynthesizerPlayer.createSynthesizerPlayer(context, context.getString(R.string.appid));
        player.setVoiceName("xiaoyan");
        player.playText(editText.getText().toString(), "tts_buffer_time=2000", synbgListener);
    }

}
