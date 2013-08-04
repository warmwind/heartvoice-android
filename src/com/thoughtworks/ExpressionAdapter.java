package com.thoughtworks;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExpressionAdapter extends ArrayAdapter<String> {

    public ExpressionAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView)super.getView(position, convertView, parent);

        if (view.getText().toString().equals(UsefulExpressionListActivity.ADD_NEW_EXPRESSION)) {
            view.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }else {
            view.setGravity(Gravity.CENTER_VERTICAL);
        }
        return view;
    }
}
