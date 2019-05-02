package com.example.android.sample.myplaceapp;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.sample.myplaceapp.camera.CameraFragment;
import com.example.android.sample.myplaceapp.camera.CameraLegacyFragment;
import com.example.android.sample.myplaceapp.camera.PictureFragment;
import com.example.android.sample.myplaceapp.location.LoggedDateFragment;
import com.example.android.sample.myplaceapp.location.LoggedMapFragment;
import com.example.android.sample.myplaceapp.location.Place;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
 * Created by Shimamura & Muta on 2017/12/13.
 */
public class MainActivity extends AppCompatActivity
        implements LoggedDateFragment.LoggedDateFragmentListener, View.OnClickListener {

    // ナビゲーションドロワーのトグル
    private ActionBarDrawerToggle mDrawerToggle;
    //追加
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //１つ前のページに戻る
        Button returnButton0 = (Button) findViewById(R.id.return_button0);
        returnButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 本日の日付文字列
        String date = String.format(Place.DATE_STR_FORMAT, System.currentTimeMillis());

        // 地図フラグメントを生成する
        LoggedMapFragment mapFragment = LoggedMapFragment.newInstance(date);
        // ギャラリーフラグメントを生成する
        PictureFragment pictureFragment = PictureFragment.newInstance(date);

        // 両フラグメントを追加する
        getFragmentManager().beginTransaction()
                .replace(R.id.MapContainer, mapFragment)
                .replace(R.id.GalleryContainer, pictureFragment)
                .commit();

        // NavigationDrawerの設定を行う
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.app_name, R.string.app_name);
        // ドロワーのトグルを有効にする
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(mDrawerToggle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        findViewById(R.id.CameraButton).setOnClickListener(this);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // ドロワーのトグルの状態を同期する
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //  if (mDrawerToggle.onOptionsItemSelected(item)) {
    //     return true;
    //  }

    //return super.onOptionsItemSelected(item);
    //}

    // ドロワーからある日付が選ばれた
    @Override
    public void onDateSelected(String date) {
        // 地図に反映する
        LoggedMapFragment mapFragment = (LoggedMapFragment) getFragmentManager()
                .findFragmentById(R.id.MapContainer);
        mapFragment.setDate(date);

        // ギャラリーに反映する
        PictureFragment pictureFragment = (PictureFragment) getFragmentManager()
                .findFragmentById(R.id.GalleryContainer);
        pictureFragment.setDate(date);
    }

    @Override
    public void onClick(View v) {
        // 「撮影する」ボタンが押された
        if (v.getId() == R.id.CameraButton) {
            Fragment cameraFragment;

            // カメラフラグメントを表示する
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                cameraFragment = new CameraFragment();
            } else {
                cameraFragment = new CameraLegacyFragment();
            }

            getFragmentManager().beginTransaction()
                    .replace(R.id.CameraContainer, cameraFragment)
                    .addToBackStack(null) // バックスタックに入れることで、戻るキーで戻れる
                    .commit();

        }
    }
}




