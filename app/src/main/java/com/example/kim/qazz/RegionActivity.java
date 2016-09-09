package com.example.kim.qazz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//dds
public class RegionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //new없엉....
    BottomSheetBehavior behavior;
    private Region_ListViewAdapter madapter;
    String text, text2, text3;
    Activity acti;
    int count = 0;
    int mWidthPixels, mHeightPixels;
    PopupWindow pwindo;
    Button btnClosePopup;
    String[] listDo = {"강원도", "경기도", "경상남도", "경상북도", "광주광역시", "대구광역시",
            "대전광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시",
            "전라남도", "전라북도", "제주특별자치도", "충청남도", "충청북도"};

    //데이터를 받아올 PHP 주소
    String url = "http://returntocs.xyz/town/";
    static ArrayList<String> address = new ArrayList<>();

    // PHP를 읽어올때 사용할 변수
    public GettingPHP gPHP;
    public GettingPHP2 gPHP2;
    private GoogleApiClient client;

//    @BindView(R.id.txtView) TextView tv;
    @BindView(R.id.bottom_button2) Button okbtn;
    @BindView(R.id.toolbar2) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.nav_view2) NavigationView navigationView;
    @BindView(R.id.drawer_layout2) DrawerLayout drawer;
    @BindView(R.id.spinnerDo) Spinner spinnerdo;
    @BindView(R.id.spinnerSi) Spinner spinnersi;
    @BindView(R.id.bottom) GridLayout bottom;
    @BindView(R.id.region_list) ListView mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_region);
        ButterKnife.bind(this);


        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }

        try{
            mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
            mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
        }catch(Exception e){

        }





        gPHP = new GettingPHP();
        acti = this;
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
//        bottom = (GridLayout) findViewById(R.id.bottom);
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
                    for (int i = 0; i < gPHP2.address.size(); i++) {
                        madapter.addItem(ContextCompat.getDrawable(RegionActivity.this, R.drawable.square), gPHP2.address.get(i));
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            LayoutInflater inflater = (LayoutInflater) RegionActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, mWidthPixels-200, mHeightPixels-1000, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            intent = new Intent(getApplicationContext(),SupportActivity_Fix.class);
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
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Region Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.example.kim.qazz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Region Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.example.kim.qazz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}