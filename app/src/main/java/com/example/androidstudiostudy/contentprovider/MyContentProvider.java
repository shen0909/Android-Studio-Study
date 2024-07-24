package com.example.androidstudiostudy.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.androidstudiostudy.database.DataBaseHelper;

public class MyContentProvider extends ContentProvider {
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private String AUTHORITIES = "com.example.androidstudiostydy.provider";
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        dataBaseHelper = new DataBaseHelper(getContext());
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        uriMatcher.addURI(AUTHORITIES, DataBaseHelper.STUTDY_SQLITE_TABLE, 0);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int code = uriMatcher.match(uri);
        if (code == 0) {
            return DataBaseHelper.STUTDY_SQLITE_TABLE;
        } else {
            return null;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String tableName = getType(uri);
        if (tableName == null) {
            return null;
        }
        return sqLiteDatabase.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

    /// 删除
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getType(uri);
        // 查询的表不存在
        if (tableName == null) {
            return -1;
        }
        return sqLiteDatabase.delete(tableName, selection, selectionArgs);
    }

    /// 更新
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String tableName = getType(uri);
        // 查询的表不存在
        if (tableName == null) {
            return -1;
        }
        return sqLiteDatabase.update(tableName, values, selection, selectionArgs);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getType(uri);
        // 查询的表不存在
        if (tableName == null) {
            return null;
        }
        long index = sqLiteDatabase.insert(tableName, null, values);
        if (index > 0) {
            return uri;
        } else {
            return null;
        }
    }
    
}