package com.HeartVoice;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.widget.AdapterView.*;

public class UsefulExpressionListActivity extends ListActivity {
    private static final int CREATE_EXPRESSION = 1;

    public static final String EXPRESSION_VALUE = "expression_value";
    public static final String ADD_NEW_EXPRESSION = "+ 添加常用语";
    private ArrayAdapter<String> adapter;
    private ExpressionDBHelper expressionDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expressionDBHelper = new ExpressionDBHelper(getApplicationContext());
        adapter = new ExpressionAdapter(this, R.layout.list_expressions);
        setListAdapter(adapter);
        initListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshExpressions();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_expressions:
                String expression = adapter.getItem(info.position);
                expressionDBHelper.deleteExpression(expression);
                adapter.remove(expression);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.expression_context_menu, menu);
    }

    private void refreshExpressions() {
        adapter.clear();
        adapter.add(ADD_NEW_EXPRESSION);
        for (String expression : expressionDBHelper.getAllExpressions()) {
            adapter.add(expression);
        }
        adapter.notifyDataSetChanged();
    }

    private void initListView() {
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(getApplicationContext(), UsefulExpressionActionActivity.class);
                    startActivityForResult(i, CREATE_EXPRESSION);
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXPRESSION_VALUE, ((TextView) view).getText());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
        registerForContextMenu(listView);
    }
}
