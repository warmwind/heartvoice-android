package com.HeartVoice;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ExpressionActionView extends LinearLayout {
    private EditText expressionEditText;
    private Button saveExpressionButton;

    public ExpressionActionView(final Context context) {
        super(context);
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.expresion_action, this, true);

        final ExpressionDBHelper expressionDBHelper = new ExpressionDBHelper(context);
        expressionEditText = (EditText) findViewById(R.id.expression_edit_text);
        saveExpressionButton = (Button) findViewById(R.id.save_expression_button);

        saveExpressionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expression = expressionEditText.getText().toString();
                if (!expression.trim().equals("")){
                    expressionDBHelper.addExpression(expression);
                    ((Activity)context).setResult(Activity.RESULT_OK);
                    ((Activity)context).finish();
                }
            }
        });
    }

    public ExpressionActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, final int h, int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        android.os.Handler handler = getHandler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (h > oldh && oldh > 0) {
                    expressionEditText.setTextSize(46);
                } else if (h < oldh) {
                    expressionEditText.setTextSize(20);
                }
            }
        });
    }

}
