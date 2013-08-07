package com.thoughtworks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.EditText;
import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;

public class SynthesizerDialogInitializer {
    private final Context context;
    private final EditText editText;
    private AlertDialog messageBox;
    private SynthesizerPlayer player;

    public SynthesizerDialogInitializer(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;

    }

    public void show() {
        initMessageBox();

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
                messageBox.setMessage("正在朗读...");
            }

            public void onEnd(SpeechError error) {
                messageBox.dismiss();
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
            }
        };
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String voice = sp.getString(SettingsActivity.KEY_DEFAULT_VOICE, "xiaoyan");

        player = SynthesizerPlayer.createSynthesizerPlayer(context, context.getString(R.string.appid));
        player.setVoiceName(voice);
        player.playText(editText.getText().toString(), "tts_buffer_time=2000", synbgListener);

    }

    private void initMessageBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setMessage("正在准备...")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(player != null){
                            player.cancel();
                        }
                    }
                });

        messageBox = alertDialogBuilder.create();
        messageBox.show();
    }

}
