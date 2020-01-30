package br.edu.fatec.aula.lendingstuffapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LendingStuffSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LendingStuffDB";

    private static final int DATABASE_VERSION = 1;

    public LendingStuffSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(ItemSchema.CREATE_TABLE_ITEM);
        } catch (Exception e){
            Log.v(DATABASE_NAME, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(ItemSchema.DROP_TABLE_ITEM);
            onCreate(sqLiteDatabase);
        } catch (Exception e){
            Log.v(DATABASE_NAME, e.getMessage());
        }
    }
}
