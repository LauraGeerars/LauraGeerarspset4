package com.example.laurageerars.laurageerarspset4;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

/**
 * Created by laurageerars on 19-11-17.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;
    private static String DB = "todos";
    private static int version = 1;
    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public static TodoDatabase getInstance(Context context){
        if (instance == null){
            instance = new TodoDatabase(context.getApplicationContext(), DB, null, version);
        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INTEGER);");
        // got it with help from: https://stackoverflow.com/questions/16561567/insert-values-into-sqlite-database
        ContentValues values = new ContentValues();
        values.put("title", "Do laundry");
        values.put("completed", 0);
        db.insert("todos",null, values);
        values.put("title", "Get started");
        values.put("completed", 0);
        db.insert("todos",null, values);
        values.put("title", "Moar items");
        values.put("completed", 0);
        db.insert("todos",null, values);
        //testDB();
    }

    /*public void testDB() {
        //SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", "Do laundry");
        values.put("completed", 1);
        db.insert("todos",null,values);
        values.put("title", "Get started");
        values.put("completed", 0);
        db.insert("todos",null,values);
        values.put("title", "Moar items");
        values.put("completed", 0);
        db.insert("todos",null,values);

    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(db);
    }

    public Cursor selectAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos", null);
        return cursor;
    }

    public void insert(String title, int completed){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("completed", completed);
        db.insert("todos",null, values);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed);
        db.update("todos", values,"_id=" + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos","_id=" + id, null);
    }
}
