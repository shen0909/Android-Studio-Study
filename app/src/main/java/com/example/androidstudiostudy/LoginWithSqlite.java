package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidstudiostudy.adapter.recyclerViewAdapter;
import com.example.androidstudiostudy.data.UserBean;

import java.util.ArrayList;

public class LoginWithSqlite extends AppCompatActivity {

    EditText name, phone, passwaord;
    Button add, delete,change;
    ArrayList<UserBean> items = new ArrayList<>();
    DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginWithSqlite.this);
    RecyclerView recyclerView;
    recyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_sqlite);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        change = findViewById(R.id.change);
        name = findViewById(R.id.nameTxt);
        phone = findViewById(R.id.phoneTxt);
        passwaord = findViewById(R.id.pwdTxt);
        recyclerView = findViewById(R.id.recycleView11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initRecycleView();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean = new UserBean(name.getText().toString(), passwaord.getText().toString(), phone.getText().toString());
                boolean success = dataBaseHelper.addOne(userBean);
                if (success) {
                    Toast.makeText(LoginWithSqlite.this, userBean.getName() + userBean.getPhone() + userBean.getPassWord(), Toast.LENGTH_LONG).show();
                    initRecycleView();
                } else {
                    Toast.makeText(LoginWithSqlite.this, "添加失败", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void initRecycleView() {
        items = dataBaseHelper.getList();
        adapter = new recyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}