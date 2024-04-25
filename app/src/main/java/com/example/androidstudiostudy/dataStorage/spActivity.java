package com.example.androidstudiostudy.dataStorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidstudiostudy.R;

public class spActivity extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        TextView tv= findViewById(R.id.showSp);
        sp = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data","这是第一个数据");
        editor.commit();
        tv.setText(sp.getString("data","没有保存key为data的数据"));
    }
}