package com.example.panicproject;

import android.provider.BaseColumns;

public class ItemContract {

    private ItemContract() {}

    public static final class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "itemList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
