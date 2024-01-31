package com.example.androidstudiostudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.androidstudiostudy.data.UserBean;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUTDY_SQLITE_TABLE = "STUTDY_SQLITE_TABLE";
    // ----列名
    public static final String COLUNM_ID = "ID";
    public static final String COLUNM_NAME = "COLUNM_NAME";
    public static final String COLUNM_PHONE = "COLUNM_PHONE";
    public static final String COLUNM_PASSWORD = "COLUNM_PASSWORD";
    private final Context context;

    //构造函数参数
    // context 帮助找到数据库
    // 数据库的名称
    // 游标工厂对象 可以为null (作用是什么？)
    // 版本号
    public DataBaseHelper(@Nullable Context context) {
        // 写死传递给父类构造函数的参数
        super(context, "database.db", null, 1);
        this.context = context;
    }

    @Override
    // 第一次尝试访问数据库对象时会调用该方法创建新表
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建新表的语句
        String createNewTableSqlStr = "CREATE TABLE " + STUTDY_SQLITE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNM_NAME + " TEXT," + COLUNM_PHONE + " TEXT," + COLUNM_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createNewTableSqlStr);
    }

    @Override
    // 当数据库的版本号发生变化时会调用该方法
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /// CRUD 操作 C-添加 R-查询 U-修改 D-删除
    /* 1.打开数据库
     * 2.添加操作语句*/
    // 添加语句
    public boolean addOne(UserBean userBean) {
        //获取要编写的唯一一个数据库
        /* getWritableDatabase SQLiteOpenHelper中的方法*/
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // 有点像flutter里的键值对
        ContentValues cv = new ContentValues();
        cv.put(COLUNM_NAME, userBean.getName());
        cv.put(COLUNM_PHONE, userBean.getPhone());
        cv.put(COLUNM_PASSWORD, userBean.getPassWord());

        // 参数：1.表名 2.第二个参数是什么？ 3。内容
        long insert = sqLiteDatabase.insert(STUTDY_SQLITE_TABLE, null, cv);
        return insert != -1;
    }

    // 删除语句 根据id来删除
    public boolean deleteOne(UserBean userBean) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        System.out.println("要删除的id:" + userBean.getId());
        String deleteSQLStr = " DELETE FROM " + STUTDY_SQLITE_TABLE + " WHERE " + COLUNM_ID + " = " + userBean.getId();
        /* 删除语句返回的是受影响的行数
         * 行数>0返回true 否则返回false */
        int result = sqLiteDatabase.delete(STUTDY_SQLITE_TABLE, COLUNM_ID + " = ?", new String[]{String.valueOf(userBean.getId())});
        return result > 0;
    }

    // 获取数据
    public ArrayList<UserBean> getList() {
        ArrayList<UserBean> returnList = new ArrayList<>();
        // 从数据库中查询
        String querySqlStr = "SELECT * FROM " + STUTDY_SQLITE_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase(); //可读
        Cursor cursor = sqLiteDatabase.rawQuery(querySqlStr, null);//返回一个游标类型，什么是游标？
        /* sqlite中的游标是结果集*/
        // 检查当前查询是否返回正确的数据，使用语句 cursor.moveToFirst() ----true 有结果 false 没有
        if (cursor.moveToFirst()) {
            // 如果有结果，则循环遍历游标，为每一行创建一个对象
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String pwd = cursor.getString(3);
                UserBean userBean = new UserBean(name, pwd, phone);
                userBean.setId(id);
                returnList.add(userBean);
            } while (cursor.moveToNext());
        } else {
            returnList.add(new UserBean("没有", "none", "none"));
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public boolean updataInfo(UserBean userBean) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNM_NAME, userBean.getName());
        cv.put(COLUNM_PHONE, userBean.getPhone());
        cv.put(COLUNM_PASSWORD, userBean.getPassWord());
        int result = sqLiteDatabase.update(STUTDY_SQLITE_TABLE, cv, "ID = ?", new String[]{String.valueOf(userBean.getId())});
        return result > 0;
    }

}
