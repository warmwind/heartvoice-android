package com.thoughtworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.umeng.analytics.MobclickAgent;

public class MyActivity extends Activity {
    private static final int EXPRESSIONS = 1;
    private static final int SETTINGS = 2;
    private MainView mainView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
