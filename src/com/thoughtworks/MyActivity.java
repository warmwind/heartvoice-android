package com.thoughtworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.useful_expressions:
                i = new Intent(this.getApplicationContext(), UsefulExpressionListActivity.class);
                startActivityForResult(i, EXPRESSIONS);
                return true;
            case R.id.settings:
                i = new Intent(this.getApplicationContext(), SettingsActivity.class);
                startActivityForResult(i, SETTINGS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

}
