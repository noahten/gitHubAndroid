package com.example.android.sample.myplaceapp;

/**
 * Created by Shimamura on 2017/12/13.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

//アクティビティの起動
public class ActivityEx extends Activity
        implements View.OnClickListener{//追加TextToSpeech.OnInitListener
    private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final static String TAG_WEB1   = "google";
    private final static String TAG_WEB2   = "weather";
    //private final static String TAG_MAP   = "map";
    private final static String TAG_LOCATION  = "state";
    //private final static String TAG_NAVI  = "navigation";
    private final static String TAG_SETUP = "setup";
    private final static String TAG_HELLO = "hello";
    private final static String TAG_GO = "comeback";

    private TextToSpeech textToSpeech;

    //アクティビティ起動時に呼ばれる
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //レイアウトの生成
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        //ボタンの生成
        layout.addView(makeButton("1 Webページの表示:Google", TAG_WEB1));
        layout.addView(makeButton("2 お天気", TAG_WEB2));
        layout.addView(makeButton("3 位置情報のON.OFF", TAG_LOCATION));
        layout.addView(makeButton("4 設定画面の表示", TAG_SETUP));
        layout.addView(makeButton("5 遊園地までの案内", TAG_HELLO));
        layout.addView(makeButton("6 遊園地内の案内", TAG_GO));

        // 初期化が終わるとonInitが呼ばれる
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    if (textToSpeech.isLanguageAvailable(Locale.JAPANESE) >= TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        // speakで読み上げ
                        textToSpeech.speak("こんにちは。　天気を見る場合は2番を、遊園地までの案内を見る場合は5番を、遊園地内の案内を見る場合は6番を押してください"
                                , textToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

    }

    //ボタンの生成
    private Button makeButton(String text, String tag) {
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        //追加
        button.setTextSize(20);
        button.setHeight(200);
        button.setWidth(600);

        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        return button;
    }

    //ボタンクリック時に呼ばれる
    public void onClick(View v) {
        String tag = (String)v.getTag();

        try {
            //Webページの表示(1)
            if (TAG_WEB1.equals(tag)) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                        onDestroy();
                startActivity(intent);
            }

            else if (TAG_WEB2.equals(tag)) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://weather.yahoo.co.jp/weather/"));
                        onDestroy();
                startActivity(intent);
            }

            //位置情報のオンオフ(3)
            else if (TAG_LOCATION.equals(tag)) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                onDestroy();
                startActivity(intent);
            }

            //設定画面の表示(5)
            else if (TAG_SETUP.equals(tag)) {
                Intent intent = new Intent("android.settings.SETTINGS");
                onDestroy();
                startActivity(intent);
            }
            //起動
            else if (TAG_HELLO.equals(tag)) {
                Intent intent = new Intent(ActivityEx.this,ActivitySecond.class);
                onDestroy();
                startActivity(intent);

            }
            //起動
            else if (TAG_GO.equals(tag)) {
                Intent intent = new Intent(getApplication(),TopScreen.class);
                onDestroy();
                startActivity(intent);
            }
        } catch (Exception e) {
            toast(e.getMessage());
        }


    }

    private void toast(String message) {
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