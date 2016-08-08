package com.example.kim.qazz;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
<<<<<<< HEAD
 * Created by kim on 16. 8. 8.
 */
public class RegionNewActivity extends AppCompatActivity implements OnMapReadyCallback {
    Double latitude;
    Double longitude;
    String townName;
    String townFact;
    String townGood;
    String townBad;

    //static final LatLng SEOUL = new LatLng(35.1997796, 128.60543);
    private GoogleMap googleMap;
    TextView tv = null;
    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng city = new LatLng(latitude, longitude);
        googleMap = map;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

        Marker seoul = googleMap.addMarker(new MarkerOptions().position(city)
                .title("Seoul"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_region_new);

        tv = (TextView) findViewById(R.id.txtView);
        Intent myintent = getIntent();
        latitude = myintent.getDoubleExtra("lat", 37.6);
        longitude = myintent.getDoubleExtra("long", 127.0);
        townName = myintent.getStringExtra("name");
        townFact = myintent.getStringExtra("fact");
        townGood = myintent.getStringExtra("good");
        townBad = myintent.getStringExtra("bad");
        if(townGood.equals("NULL")){
            townGood = "정보없음";
        }
        if(townBad.equals("NULL")){
            townBad = "정보없음";
        }

        townFact = townFact.substring(1,townFact.length()-1);
        Log.i("위도/경도/마을이름/시설/굿/뱃", latitude +" , " + longitude + " , " + townName + " , "
                + townFact + " , " + townGood + " , " + townBad);
        tv.append("마을이름  :  "+townName+"\n\n"+"마을시설  :  "+townFact+"\n\n"+"Good  :  "+townGood+"\n\n"+"Bad  :  "+townBad+"\n");
        //tv.setText("마을이름  :  "+townName+"\n\n"+"마을시설  :  "+townFact+"\n\n"+"Good  :  "+townGood+"\n\n"+"Bad  :  "+townBad+"\n");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}