package com.example.androidstudiostudy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidstudiostudy.MainActivity;
import com.example.androidstudiostudy.R;

public class ConstraintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);
    }

    public void jump(View view) {
        Intent intent = new Intent(ConstraintActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
