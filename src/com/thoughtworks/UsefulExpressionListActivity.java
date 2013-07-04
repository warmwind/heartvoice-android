package com.thoughtworks;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class UsefulExpressionListActivity extends ListActivity {

    static final String[] EXPRESSIOINS = new String[] {"请帮我个忙好吗", "您能给我们拍张照片吗", "请问这里是什么地方", "救命啊，救命啊" };
    public static final String EXPRESSION_VALUE = "expression_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_expressions, EXPRESSIOINS));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXPRESSION_VALUE, ((TextView) view).getText());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
