package com.thoughtworks;


import android.app.Activity;
import android.os.Bundle;

public class UsefulExpressionActionActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ExpressionActionView(this));
    }
}
