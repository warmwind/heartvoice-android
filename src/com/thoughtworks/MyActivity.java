package com.thoughtworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyActivity extends Activity {
    private static final int EXPRESSIONS = 1;
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

        switch (item.getItemId()) {
            case R.id.useful_expressions:
                Intent i = new Intent(this.getApplicationContext(), UsefulExpressionListActivity.class);
                startActivityForResult(i, EXPRESSIONS);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (EXPRESSIONS) : {
                if (resultCode == Activity.RESULT_OK) {
                    String newText = data.getStringExtra(UsefulExpressionListActivity.EXPRESSION_VALUE);
                    mainView.editText.setText(newText);
                }
                break;
            }
        }
    }

}
