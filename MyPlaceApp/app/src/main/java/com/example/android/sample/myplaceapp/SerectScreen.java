package com.example.android.sample.myplaceapp;

/**
 * Created by Shimamura on 2017/12/11.
 */

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageView;package com.example.android.sample.myplaceapp.location;

/**
 * Created by Shimamura on 2017/12/11.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;


public class SerectScreen extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serectscreen);

        // 初期化が終わるとonInitが呼ばれる
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    if (textToSpeech.isLanguageAvailable(Locale.JAPANESE) >= TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        // speakで読み上げ
                        textToSpeech.speak("ここでは、地点0からの場所案内をします。行きたい場所のボタンを押すことで案内ルートを表示します。"
                                , textToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        imageView2.setImageResource(R.drawable.position0);

        //2号館ボタン
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_2.class);
                startActivity(intent);
            }
        });

        //講義等ボタンの追加
        Button sendButton0 = (Button) findViewById(R.id.send_button0);
        sendButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_kougi.class);
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

        //戻るの追加
        Button sendButton2 = (Button) findViewById(R.id.send_button2);
        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SerectPosition.class);
                startActivity(intent);
            }
        });

        //12号館の追加
        Button sendButton3 = (Button) findViewById(R.id.send_button3);
        sendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_12.class);
                startActivity(intent);
            }
        });

        //3号館の追加
        Button sendButton4 = (Button) findViewById(R.id.send_button4);
        sendButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_3.class);
                startActivity(intent);
            }
        });

        //4号館の追加
        Button sendButton5 = (Button) findViewById(R.id.send_button5);
        sendButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_4.class);
                startActivity(intent);
            }
        });

        //5号館の追加
        Button sendButton6 = (Button) findViewById(R.id.send_button6);
        sendButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_5.class);
                startActivity(intent);
            }
        });

        //6号館の追加
        Button sendButton7 = (Button) findViewById(R.id.send_button7);
        sendButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_6.class);
                startActivity(intent);
            }
        });

        //7号館の追加
        Button sendButton8 = (Button) findViewById(R.id.send_button8);
        sendButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_7.class);
                startActivity(intent);
            }
        });

        //8号館の追加
        Button sendButton9 = (Button) findViewById(R.id.send_button9);
        sendButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Position_8.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        // 終了時はtextToSpeechを終了させる
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
