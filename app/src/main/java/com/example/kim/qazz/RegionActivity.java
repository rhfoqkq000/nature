package com.example.kim.qazz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
//dds
public class RegionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //new없엉....
    GridLayout bottom;
    BottomSheetBehavior behavior;
    private ListView mlist;
    private Region_ListViewAdapter madapter;
    Spinner spinnerdo;
    Spinner spinnersi;
    String text;
    String text2;
    String text3;
    Activity acti;
    int count = 0;
    Button okbtn;
    String[] listDo = {"강원도", "경기도", "경상남도", "경상북도", "광주광역시", "대구광역시",

            "대전광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시",

            "전라남도", "전라북도", "제주특별자치도", "충청남도", "충청북도"};

    //데이터를 받아올 PHP 주소
    String url = "http://returntocs.xyz/town/";
    static ArrayList<String> address = new ArrayList<>();

    // 데이터를 보기위한 TextView
    TextView tv;

    // PHP를 읽어올때 사용할 변수
    public GettingPHP gPHP;
    public GettingPHP2 gPHP2;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        gPHP = new GettingPHP();
        acti = this;
        tv = (TextView) findViewById(R.id.txtView);
        okbtn = (Button) findViewById(R.id.bottom_button2);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        //Spinner
        spinnerdo = (Spinner) findViewById(R.id.spinnerDo);
        spinnersi = (Spinner) findViewById(R.id.spinnerSi);
        ArrayAdapter<String> ladapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listDo);
        ladapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinnerdo.setAdapter(ladapter);
        text = spinnerdo.getSelectedItem().toString();
        Log.i("행정구역 text : ", text);

        spinnerdo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                count++;
                if (count > 1) {
                    gPHP = null;
                    gPHP = new GettingPHP();
                    count--;
                }
                url = "http://returntocs.xyz/town/";
                text = spinnerdo.getSelectedItem().toString();
                Log.i("text 2", text);
                gPHP.execute(url + text);

                Handler handler = new Handler();
                new Handler().postDelayed(new Runnable() {// 1 초 후에 실행
                    @Override
                    public void run() {
                        if (gPHP.result_List.size() == 0) {
                            gPHP.result_List.add(0, "------");
                        }
                        ArrayAdapter<String> ladapter2 = new ArrayAdapter<String>(
                                acti, android.R.layout.simple_spinner_item, gPHP.result_List);
                        ladapter2.setDropDownViewResource(
                                android.R.layout.simple_spinner_dropdown_item);
                        Log.i("시/군", "" + gPHP.result_List);
                        spinnersi.setAdapter(ladapter2);
                    }
                }, 600);

                spinnersi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        text2 = spinnersi.getSelectedItem().toString();
                        Log.i("스피너2", text2);
                    }
                    public void onNothingSelected(AdapterView parent) {
                        // Do nothing.
                    }
                });
            }
            public void onNothingSelected(AdapterView parent) {
                // Do nothing.
            }
        });

        //region_Bottom
        bottom = (GridLayout) findViewById(R.id.bottom);
        behavior = BottomSheetBehavior.from(bottom);
        behavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150.f, getResources().getDisplayMetrics()));
        behavior.setHideable(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onButtonClick(View view) {
        if (text2 == "------") {
            Toast.makeText(getApplicationContext(), "다른 시/도를 선택해주세요!", Toast.LENGTH_LONG).show();
        } else {
            text3 = text + " " + text2;
            Log.i("합친 주소 보내기 : ", text3);
            Toast.makeText(getApplicationContext(), text3 + " 을(를) 검색합니다.", Toast.LENGTH_LONG).show();
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            //List 부분
            gPHP2 = new GettingPHP2();
            gPHP2.execute(url + text3);

            madapter = new Region_ListViewAdapter();
            mlist = (ListView) findViewById(R.id.region_list);


            Handler handler = new Handler();
            new Handler().postDelayed(new Runnable() {// 1 초 후에 실행

                @Override
                public void run() {
                    mlist.setAdapter(madapter);
                    Log.i("두번째 execute list : ", "" + gPHP2.address);
//                    Log.i("사이즈 ", "" + gPHP2.address.size());
                    for (int i = 0; i < gPHP2.address.size(); i++) {
                        //list의 아이콘와 내용을 적는 곳
                        madapter.addItem(ContextCompat.getDrawable(RegionActivity.this, R.drawable.school1), gPHP2.address.get(i));
//                        madapter.addItem(getResources().getDrawable(R.drawable.hospital1), gPHP2.address.get(i));
                    }
                }
            }, 200);

            mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ListData mData = madapter.mlistData.get(i);
                    Toast.makeText(RegionActivity.this, mData.mtext, Toast.LENGTH_SHORT).show();
                    Log.i("위도", "" + gPHP2.latitude.get(i));
                    Log.i("경도", "" + gPHP2.longitude.get(i));
                    Intent myIntent = new Intent(getApplicationContext(), RegionNewActivity.class);
                    myIntent.putExtra("lat", gPHP2.latitude.get(i));
                    myIntent.putExtra("long", gPHP2.longitude.get(i));
                    myIntent.putExtra("fact", gPHP2.townFact.get(i));
                    myIntent.putExtra("name", gPHP2.townName.get(i));
                    myIntent.putExtra("good", gPHP2.townGood.get(i));
                    myIntent.putExtra("bad", gPHP2.townBad.get(i));
                    startActivity(myIntent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_home) {
            intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_notice) {
            intent = new Intent(getApplicationContext(),NoticeActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_region) {
            intent = new Intent(getApplicationContext(),RegionActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_support) {
            intent = new Intent(getApplicationContext(),SupportActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_success) {
            intent = new Intent(getApplicationContext(),SuccessActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Region Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.kim.qazz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Region Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.kim.qazz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}