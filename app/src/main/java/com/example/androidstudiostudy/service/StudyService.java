package com.example.androidstudiostudy.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class StudyService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_service);

    }

    public void serviceAction(View view) {
        int id = view.getId();
        Intent intent = new Intent(this,OneService.class);
        if(id == R.id.bindService){
            // 绑定service
        } else if (id == R.id.stopService) {
            stopService(intent);
        } else {
            startService(intent);
        }
    }
}