package com.thoughtworks;

import android.provider.BaseColumns;

public final class ExpressionContract {
    public ExpressionContract() {}

    public static abstract class ExpressionEntry implements BaseColumns {
        public static final String TABLE_NAME = "expression";
        public static final String COLUMN_NAME_EXPRESSION = "content";
    }
}