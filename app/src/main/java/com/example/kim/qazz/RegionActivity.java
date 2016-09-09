package com.example.kim.qazz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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

import com.example.kim.qazz.RetrofitRegion.Region;
import com.example.kim.qazz.RetrofitRegion.Repo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        final ArrayList<String> address = new ArrayList<>();
        final ArrayList address_result = new ArrayList();

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
                text = spinnerdo.getSelectedItem().toString();
                Log.i("행정구역 text : ", text);

                Retrofit client = new Retrofit.Builder().baseUrl("http://45.32.61.201:3000/nature/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                Region region_service = client.create(Region.class);

                Call<Repo> region_call = region_service.repo(String.valueOf(text));

                region_call.enqueue(new Callback<Repo>() {
                    @Override
                    public void onResponse(Call<Repo> call, Response<Repo> response) {
                        if (response.isSuccessful()) {
                            Repo repo = response.body();
                            address.clear();
                            address_result.clear();
                            if (repo.getResult_data_maeul().size() == 0) {
                                address.add(0, "--------");
                            } else {
                                for (int i = 0; i < repo.getResult_data_maeul().size(); i++) {
                                    String address_split = repo.getResult_data_maeul().get(i).getAddress();
                                    address_split = address_split.split(" ")[1];
                                    address.add(i, address_split);
                                }
                            }

                            HashSet hs = new HashSet(address);
                            Iterator it = hs.iterator();
                            while (it.hasNext()) {
                                address_result.add(it.next());
                            }
                            Collections.sort(address_result, String.CASE_INSENSITIVE_ORDER);
                            Log.e("address_result", "" + address_result);

                            ArrayAdapter<String> ladapter2 = new ArrayAdapter<String>(
                                    acti, android.R.layout.simple_spinner_item, address_result);
                            ladapter2.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            Log.i("시/군", "" + address_result);

                            spinnersi.setAdapter(ladapter2);

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

                        } else {
                            Log.e("실패?ㅠㅠ", "" + text);
                        }

                    }

                    @Override
                    public void onFailure(Call<Repo> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        final ArrayList<String> townName = new ArrayList<>();
        final ArrayList<String> address2 = new ArrayList<>();
        final ArrayList<String> townFact = new ArrayList<>();
        final ArrayList<String> townGood = new ArrayList<>();
        final ArrayList<String> townBad = new ArrayList<>();
        final ArrayList<Double> latitude = new ArrayList<>();
        final ArrayList<Double> longitude = new ArrayList<>();

        if (text2 == "--------") {
            Toast.makeText(getApplicationContext(), "다른 시/도를 선택해주세요!", Toast.LENGTH_LONG).show();
        } else {
            text3 = text + " " + text2;
            Log.i("합친 주소 보내기 : ", text3);
            Toast.makeText(getApplicationContext(), text3 + " 을(를) 검색합니다.", Toast.LENGTH_LONG).show();
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            //List 부분
            Retrofit client2 = new Retrofit.Builder().baseUrl("http://45.32.61.201:3000/nature/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            Region region_service2 = client2.create(Region.class);

            Call<Repo> region_call2 = region_service2.repo(String.valueOf(text3));

            region_call2.enqueue(new Callback<Repo>() {
                @Override
                public void onResponse(Call<Repo> call, Response<Repo> response) {
                    if (response.isSuccessful()) {
                        Repo repo = response.body();
                        townName.clear();
                        for (int i = 0; i < repo.getResult_data_maeul().size(); i++) {
                            townName.add(i, repo.getResult_data_maeul().get(i).getTownName());
                            address2.add(i, repo.getResult_data_maeul().get(i).getAddress());
                            townFact.add(i, repo.getResult_data_maeul().get(i).getTownFact());
                            townGood.add(i, repo.getResult_data_maeul().get(i).getTownGood());
                            townBad.add(i, repo.getResult_data_maeul().get(i).getTownBad());
                            latitude.add(i, repo.getResult_data_maeul().get(i).getLatitude());
                            longitude.add(i, repo.getResult_data_maeul().get(i).getLongitude());
                        }

                        madapter = new Region_ListViewAdapter();
                        mlist = (ListView) findViewById(R.id.region_list);

                        mlist.setAdapter(madapter);
                        Log.i("list 목록 : ", "" + townName);
                        for (int i = 0; i < townName.size(); i++) {
                            madapter.addItem(ContextCompat.getDrawable(RegionActivity.this, R.drawable.home), townName.get(i));
                        }

                    } else {
                    }
                }

                @Override
                public void onFailure(Call<Repo> call, Throwable t) {

                }

            });

            mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ListData mData = madapter.mlistData.get(i);
                    Toast.makeText(RegionActivity.this, mData.mtext, Toast.LENGTH_SHORT).show();
                    Log.i("위도", "" + latitude.get(i));
                    Log.i("경도", "" + longitude.get(i));
                    Intent myIntent = new Intent(getApplicationContext(), RegionNewActivity.class);
                    myIntent.putExtra("lat", latitude.get(i));
                    myIntent.putExtra("long", longitude.get(i));
                    myIntent.putExtra("add", address2.get(i));
                    myIntent.putExtra("fact", townFact.get(i));
                    myIntent.putExtra("name", townName.get(i));
                    myIntent.putExtra("good", townGood.get(i));
                    myIntent.putExtra("bad", townBad.get(i));
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