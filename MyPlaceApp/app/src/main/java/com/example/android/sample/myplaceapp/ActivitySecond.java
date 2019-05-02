package com.example.android.sample.myplaceapp;

/**
 * Created by Shimamura & Muta on 2017/12/13.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

import java.util.Locale;


public class ActivitySecond extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener{

    private GoogleMap mMap;
    private TextToSpeech textToSpeech;

    static double lat;
    static double lng;

    private static String loc() {
        final String data = "google.navigation:q="
                + lat
                +','
                + lng;
        return data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map0);
        mapFragment.getMapAsync(this);


        //追加
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(loc());
                //google.navigation:q=35.919053,139.911136
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        // 初期化が終わるとonInitが呼ばれる
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    if (textToSpeech.isLanguageAvailable(Locale.JAPANESE) >= TextToSpeech.LANG_AVAILABLE) {
                        textToSpeech.setLanguage(Locale.JAPANESE);
                        // speakで読み上げ
                        textToSpeech.speak("右上のメニューボタンを押して、目的地を設定し、案内を開始してください。"
                                , textToSpeech.QUEUE_FLUSH, null);
                    }
                }
            }
        });

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lat=35.9178581;
        lng=139.9099878;
        loc();
        LatLng rika = new LatLng(lat,lng);//初期座標
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(rika);
        builder.zoom(13.0f);
        builder.bearing(0);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(builder.build()));

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "東京ネズミ－ランド");
        menu.add(0, 1, 1, "東京UFJ");
        menu.add(0, 2, 2, "千葉ドイツ村");
        menu.add(0, 3, 3, "ハウス天ボス");
        menu.add(0, 4, 4, "浅草草屋敷");
        return true;
    }
    //
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemID = item.getItemId();


        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.zoom(15.0f);
        builder.bearing(0);

        switch (itemID) {
            case 0:
                lat=35.635878;
                lng=139.878648;
                loc();
                LatLng b = new LatLng(lat,lng);
                builder.target(b);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(b).title("東京ネズミ－ランド"));

                break;
            case 1:
                lat=34.665442;
                lng=135.432338;
                loc();
                LatLng z = new LatLng(lat,lng);
                builder.target(z);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(z).title("東京UFJ"));
                break;
            case 2:
                lat=35.2420;
                lng=140.0334;
                loc();
                LatLng q = new LatLng(lat,lng);
                builder.target(q);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(q).title("千葉ドイツ村"));
                break;
            case 3:
                lat=33.050937;
                lng=129.472358;
                loc();
                LatLng r = new LatLng(lat,lng);
                builder.target(r);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(r).title("ハウス天ボス"));
                break;
            case 4:
                lat=35.425566;
                lng=139.474084;
                loc();
                LatLng f = new LatLng(lat,lng);
                builder.target(f);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(f).title("浅草草屋敷"));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
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