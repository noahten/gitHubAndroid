package com.example.android.sample.myplaceapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by Shimamura & Muta on 2017/12/13.
 */

public class FinalScreen extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.finalscreen);

        // 初期化が終わるとonInitが呼ばれる
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    if (textToSpeech.isLanguageAvailable(Locale.JAPANESE) >= TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        // speakで読み上げ
                        textToSpeech.speak("ご利用ありがとうございました。"
                                , textToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

        //TOP画面に戻る
        Button sendButton0 = (Button) findViewById(R.id.return_button);
        sendButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ActivityEx.class);
                startActivity(intent);
            }
        });
        //１つ前のページに戻る
        Button returnButton0 = (Button) findViewById(R.id.return_button0);
        returnButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
