package com.example.android.sample.myplaceapp.location;

/**
 * Created by Shimamura & Muta on 2017/12/13.
 */

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;

import com.example.android.sample.myplaceapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class LiveSpotFragment extends MapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng rika = new LatLng(35.9178581,139.9099878);//初期座標
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(rika);
        builder.zoom(13.0f);
        builder.bearing(0);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(builder.build()));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "アトラクションA");
        menu.add(0, 1, 1, "アトラクションB");
        menu.add(0, 2, 2, "アトラクションC");
        menu.add(0, 3, 3, "アトラクションD");
        menu.add(0, 4, 4, "アトラクションE");
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
                LatLng b = new LatLng(35.6725381,139.7353057);
                builder.target(b);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(b).title("アトラクションA"));
                break;
            case 1:
                LatLng z = new LatLng(35.6259667,139.7823094);
                builder.target(z);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(z).title("アトラクションB"));
                break;
            case 2:
                LatLng q = new LatLng(35.660877,139.697578);
                builder.target(q);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(q).title("アトラクションC"));
                break;
            case 3:
                LatLng r = new LatLng(34.660877,133.697578);
                builder.target(r);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(r).title("アトラクションC"));
                break;
            case 4:
                LatLng f = new LatLng(35.660877,139.697578);
                builder.target(f);
                mMap.moveCamera(
                        CameraUpdateFactory.newCameraPosition(builder.build()));
                mMap.addMarker(
                        new MarkerOptions().position(f).title("アトラクションC"));
                break;
            default:
                break;
        }
        return true;
    }
}