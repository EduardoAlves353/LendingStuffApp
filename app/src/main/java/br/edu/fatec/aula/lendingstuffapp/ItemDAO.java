package br.edu.fatec.aula.lendingstuffapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements SQLiteGenericDAO<Item> {

    private LendingStuffSQLHelper helper;

    public ItemDAO(Context context) {
        helper = new LendingStuffSQLHelper(context);
    }

    @Override
    public long create(Item item) throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = getContentValue(item);
        long id = db.insert(ItemSchema.TABLE_ITEM, null, cv);
        if (id != -1){
            item.setId(id);
        }
        db.close();
        return id;
    }

    public ContentValues getContentValue(Item item) {
        ContentValues cv = new ContentValues();
        if (item.getId() != 0)
            cv.put(ItemSchema.COLUMN_ID, item.getId());

        cv.put(ItemSchema.COLUMN_DESCRIPTION, item.getDescription());
        cv.put(ItemSchema.COLUMN_NAME, item.getName());
        cv.put(ItemSchema.COLUMN_DATE, item.getDate());
        return cv;
    }

    @Override
    public Item readById(long id) throws Exception {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] args = { String.valueOf(id) };
        Cursor cursor = db.rawQuery(ItemSchema.SELECT_BY_ID,  args);

        if (cursor != null){
            cursor.moveToNext();
        }

        Item item = getItemFromCursor(cursor);

        cursor.close();
        db.close();
        return item;
    }

    private Item getItemFromCursor(Cursor cursor) {

        long id = cursor.getLong(0);
        String description = cursor.getString(1);
        String name = cursor.getString(2);
        String date = cursor.getString(3);

        Item item = new Item(id, description, name, date);
        return item;
    }

    @Override
    public List<Item> readAll() throws Exception {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Item> items = new ArrayList<>();
        Cursor cursor = db.rawQuery(ItemSchema.SELECT_ALL, null);

        if (cursor.moveToFirst()){
            do {
                Item item = getItemFromCursor(cursor);
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }

    @Override
    public void update(Item item) throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = getContentValue(item);
        String[] args = { String.valueOf(item.getId()) };
        db.update(ItemSchema.TABLE_ITEM, cv, ItemSchema.WHERE_ID, args);
        db.close();
    }

    @Override
    public void delete(Item item) throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = { String.valueOf(item.getId()) };
        db.delete(ItemSchema.TABLE_ITEM, ItemSchema.WHERE_ID, args);
        db.close();
    }
}
