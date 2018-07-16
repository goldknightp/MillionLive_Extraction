package com.example.biei.millionlive_extraction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBAdapter {


    /**
     * フィールド変数
     */
    private SQLiteDatabase db = null;
    private DBOpenHelper dbHelper = null;
    private Context context;


    /**
     * コンストラクタ
     *
     * @param context Context
     */
    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBOpenHelper(this.context);
    }


    /**
     * DBの書き込み
     * openDB()
     *
     * @return this 自身のオブジェクト
     */
    public DBAdapter openDB() {
        db = dbHelper.getWritableDatabase();        // DBの読み書き
        return this;
    }


    /**
     * DBの読み込み
     * readDB()
     *
     * @return this 自身のオブジェクト
     */
    public DBAdapter readDB() {
        db = dbHelper.getReadableDatabase();        // DBの読み込み
        return this;
    }


    /**
     * DBを閉じる
     * closeDB()
     */
    public void closeDB() {
        db.close();     // DBを閉じる
        db = null;
    }


    /**
     * DBからデータを取得
     * getDB()
     *
     * @param querySQL String
     * @param selectionArgs String[]
     * @return Cursor
     */
    public Cursor getDB(String querySQL, String[] selectionArgs) {

        return db.rawQuery(querySQL, selectionArgs);
    }

}
