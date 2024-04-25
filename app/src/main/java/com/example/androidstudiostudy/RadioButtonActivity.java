package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RadioButtonActivity extends AppCompatActivity {
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        radioGroup = findViewById(R.id.radioGroupId);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            // checkedId 是选中的 RadioButton 的id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 找到选中的 RadioButton
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(RadioButtonActivity.this, "当前选中的单选项："+radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        ToggleButton toggleButton = findViewById(R.id.toggleButton1);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("isChecked","当前开关触发器的状态："+isChecked);

            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar);
        // 设置最大值
        seekBar.setMax(50);
        // 设置当前进度
        seekBar.setProgress(40);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 进度改变时（过程中）的回调方法
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("seekBar change","当前seekBar的进度："+progress);

            }

            // 开始时回调的方法
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("seekBar开始了","当前seekBar的进度："+seekBar.getProgress());

            }

            // 结束时回调的方法
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("seekBar结束了","当前seekBar的进度："+seekBar.getProgress());
            }
        });
    }
}