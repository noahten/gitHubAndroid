package com.example.android.sample.myplaceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Shimamura & Muta on 2017/12/13.
 */

public class Position_12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posi_12);

        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        imageView2.setImageResource(R.drawable.posi0_12);

        //到着ボタンの追加
        Button sendButton2 = (Button) findViewById(R.id.send_button2);
        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), FinalScreen.class);
                startActivity(intent);
            }
        });

        //選択画面ボタンの追加
        Button sendButton0 = (Button) findViewById(R.id.send_button0);
        sendButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SerectScreen.class);
                startActivity(intent);
            }
        });

        //現在位置の追加
        Button sendButton1 = (Button) findViewById(R.id.send_button1);
        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
