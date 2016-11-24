package com.owen.demo.charimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.owen.demo.library.CharImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        CharImageView charImageView = (CharImageView) findViewById(R.id.char_iv);
        charImageView.setText("张三");
    }
}
