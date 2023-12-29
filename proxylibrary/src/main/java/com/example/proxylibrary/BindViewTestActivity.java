package com.example.proxylibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation_lib.bindview.BindView;
import com.example.annotation_lib.test.TestOne;
import com.example.proxylibrary.bindview.BindViewHelper;

public class BindViewTestActivity extends AppCompatActivity {

    @BindView(1000011)
    public TextView textView;
    private Button button;

    @TestOne()
    private int testOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_view_test);

        BindViewHelper.bind(this);

        initView();
    }

    private void initView() {
        textView = findViewById(R.id.tv_hello);
        button = findViewById(R.id.bt_change_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("new hello");
            }
        });
    }
}