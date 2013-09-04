package com.HeartVoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ExpressionDBHelper extends SQLiteOpenHelper {

    private static final String COLUMN_NAME = ExpressionContract.ExpressionEntry.COLUMN_NAME_EXPRESSION;
    private static final String EXPRESSION_TABLE_NAME = ExpressionContract.ExpressionEntry.TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "heartvoice";

    private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE " + EXPRESSION_TABLE_NAME + " ( " +
            ExpressionContract.ExpressionEntry._ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT);";

    ExpressionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
        addExpressionWithDb("请帮我个忙好吗", db);
        addExpressionWithDb("您能给我们拍张照片吗", db);
        addExpressionWithDb("请问这里是什么地方", db);
        addExpressionWithDb("你在做什么？", db);
        addExpressionWithDb("请把菜单拿来吧。", db);
        addExpressionWithDb("请把那个给我看一下。", db);
        addExpressionWithDb("这个多少钱？", db);
        addExpressionWithDb("请问_怎么走？", db);
        addExpressionWithDb("救命啊，救命啊", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + EXPRESSION_TABLE_NAME);
        onCreate(db);
    }

    public void addExpression(String expression) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, expression);

        db.insert(EXPRESSION_TABLE_NAME, null, values);
        db.close();
    }

    private void addExpressionWithDb(String expression, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, expression);
        db.insert(EXPRESSION_TABLE_NAME, null, values);
    }

    public List<String> getAllExpressions() {
        List<String> results = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + EXPRESSION_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String expression = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                if (!expression.trim().equals("")) {
                    results.add(expression);
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return results;
    }

    public void deleteExpression(String expression) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXPRESSION_TABLE_NAME, COLUMN_NAME + " = ?",
                new String[]{expression});
        db.close();
    }
}