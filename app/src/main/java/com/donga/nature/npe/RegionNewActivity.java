package com.donga.nature.npe;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nature on 16. 8. 8.
 */
public class RegionNewActivity extends AppCompatActivity implements OnMapReadyCallback {
    Double latitude;
    Double longitude;
    String address;
    String townName;
    String townFact;
    String townGood;
    String townBad;
    @BindView(R.id.txtView) TextView tv;

    private GoogleMap googleMap;
    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng city = new LatLng(latitude, longitude);
        googleMap = map;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
 //       googleMap.setMyLocationEnabled(true);

        Marker town = googleMap.addMarker(new MarkerOptions().position(city)
                .title(townName));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 15));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_region_new);
        ButterKnife.bind(this);

        Intent myintent = getIntent();
        latitude = myintent.getDoubleExtra("lat", 37.0);
        longitude = myintent.getDoubleExtra("long", 127.0);
        address = myintent.getStringExtra("add");
        townName = myintent.getStringExtra("name");
        townFact = myintent.getStringExtra("fact");
        townGood = myintent.getStringExtra("good");
        townBad = myintent.getStringExtra("bad");
        if(townFact.substring(1,townFact.length()-1).equals("null")){
            townFact = "해당 자료 없음";
        }else{
            townFact = townFact.substring(1,townFact.length()-1);
        }
        try{
            Log.i("goodbad", townBad+","+townGood);
            if(townGood==null&&townBad==null){
                tv.append("마을이름  :  "+townName+"\n\n"+"주소  :  "+address+"\n\n"+"마을시설  :  "+townFact+"\n\n");
            }else if(townBad==null&&townGood!=null){
                tv.append("마을이름  :  "+townName+"\n\n"+"주소  :  "+address+"\n\n"+"마을시설  :  "+townFact+"\n\n선호시설  :  "+townGood);
            }else if(townGood==null&&townBad!=null){
                tv.append("마을이름  :  "+townName+"\n\n"+"주소  :  "+address+"\n\n"+"마을시설  :  "+townFact+"\n\n위해시설  :  "+townBad);
            }else{
                tv.append("마을이름  :  "+townName+"\n\n"+"주소  :  "+address+"\n\n"+"마을시설  :  "+townFact+"\n\n"+"선호시설  :  "+townGood+"\n\n"+"위해시설  :  "+townBad+"\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}