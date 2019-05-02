package com.example.android.sample.myplaceapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

/**
 * Created by Shimamura on 2017/12/12.
 */

public class SerectPosition extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serectposition);

        // 初期化が終わるとonInitが呼ばれる
                textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    if (textToSpeech.isLanguageAvailable(Locale.JAPANESE) >= TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        // speakで読み上げ
                        textToSpeech.speak("ここでは、現在近い位置を地図上の番号から指定してください。"
                                , textToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        imageView2.setImageResource(R.drawable.myposition);

        //現在位置0のボタン
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SerectScreen.class);
                startActivity(intent);
            }
        });

        //現在位置表示の追加
        Button sendButton1 = (Button) findViewById(R.id.send_button1);
        sendButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
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
