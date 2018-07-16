package com.example.biei.millionlive_extraction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * DBOpenHelperクラス
 * class:DBOpenHelper
 * extends:SQLiteOpenHelper
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    /**
     * フィールド変数
     */
    //テーブル名
    private static final String LIVE_INFO_TABLE_NAME = "live_info";
    private static final String SONG_INFO_TABLE_NAME = "song_info";


    /**
     * コンストラクタ
     *
     * @param context Context
     */
    public DBOpenHelper(Context context) {
        super(context, "million_live.db", null, 2);
    }


    /**
     * DB生成する処理
     * onCreate()
     *
     * @param db SQLiteDatabase
     */
    public void onCreate(SQLiteDatabase db) {

        String createLiveInfo = "CREATE TABLE " + LIVE_INFO_TABLE_NAME + " ("
                + "primary" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name" + " TEXT NOT NULL,"
                + "year" + " TEXT NOT NULL,"
                + "date" + " TEXT NOT NULL,"
                + "days" + " TEXT NOT NULL,"
                + "prefecture" + " TEXT NOT NULL,"
                + "place" + " TEXT NOT NULL"
                + ");";

        String createSongInfo = "CREATE TABLE " + SONG_INFO_TABLE_NAME + " ("
                + "primary" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "song" + " TEXT NOT NULL,"
                + "artist" + " TEXT NOT NULL,"
                + "lyricist" + " TEXT NOT NULL,"
                + "composer" + " TEXT NOT NULL"
                + ");";

        db.execSQL(createLiveInfo);
        db.execSQL(createSongInfo);
    }


    /**
     * DBアップグレード処理
     * onUpgrade()
     *
     * @param db SQLiteDatabase
     * @param oldVersion int
     * @param newVersion int
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // DBからテーブル削除
        db.execSQL("DROP TABLE IF EXISTS " + LIVE_INFO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SONG_INFO_TABLE_NAME);

        // テーブル生成
        onCreate(db);
    }
}
