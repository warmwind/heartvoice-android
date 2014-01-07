package com.HeartVoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.umeng.analytics.MobclickAgent;

public class MyActivity extends Activity {
    private static final int EXPRESSIONS = 1;
    private static final int SETTINGS = 2;
    private MainView mainView;
    private AudioManager audio;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        super.onCreate(savedInstanceState);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mainView = new MainView(this);
        setContentView(mainView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (EXPRESSIONS):
                if (resultCode == Activity.RESULT_OK) {
                    String newText = data.getStringExtra(UsefulExpressionListActivity.EXPRESSION_VALUE);
                    mainView.editText.setText(newText);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                return true;
            default:
                return false;
        }
    }

    public void clear(View view){
      mainView.clear();
    }

    public void goToExpressionList(View view){
        Intent intent = new Intent(this.getApplicationContext(), UsefulExpressionListActivity.class);
        startActivityForResult(intent, EXPRESSIONS);

    }

    public void goToSettings(View view){
        Intent intent = new Intent(this.getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent, SETTINGS);

    }

}
