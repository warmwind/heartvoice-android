package com.thoughtworks;

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
    private ArrayAdapter<String> adapter;
    private ExpressionDBHelper expressionDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expressionDBHelper = new ExpressionDBHelper(getApplicationContext());
        adapter = new ArrayAdapter<String>(this, R.layout.list_expressions);
        setListAdapter(adapter);
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXPRESSION_VALUE, ((TextView) view).getText());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        registerForContextMenu(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.expressions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.create_expression:
                Intent i = new Intent(this.getApplicationContext(), UsefulExpressionActionActivity.class);
                startActivityForResult(i, CREATE_EXPRESSION);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        for (String expression : expressionDBHelper.getAllExpressions()) {
            adapter.add(expression);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (CREATE_EXPRESSION): {
                break;
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
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


}
