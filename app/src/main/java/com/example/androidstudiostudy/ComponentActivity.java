package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
    }

    public void toJumpPage(View view) {
        int id = view.getId();
        if(id == R.id.toLookTV){
            Intent intent = new Intent(this,TextActivity.class);
            startActivity(intent);
        } else if (id == R.id.toLookCheck) {
            Intent intent = new Intent(this,checkBoxActivity.class);
            startActivity(intent);
        }else if (id == R.id.toLookRadio) {
            Intent intent = new Intent(this,RadioButtonActivity.class);
            startActivity(intent);
        }else if (id == R.id.toLookProgress) {
            Intent intent = new Intent(this,ProgressBarActivyty.class);
            startActivity(intent);
        }
    }
}