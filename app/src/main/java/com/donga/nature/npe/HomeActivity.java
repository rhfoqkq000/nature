package com.donga.nature.npe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//R오류가 뜬다면 Gradle Scripts -> build.gradle(Module:app)의 dependencies에 compile 'com.google.android.gms:play-services-appindexing:8.1.0'을 추가

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ProgressDialog mProgressDialog;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    int mWidthPixels, mHeightPixels;
    PopupWindow pwindo, helpwindo;
    ImageButton close;
    Button btnClosePopup,next, pre;
    CardView next_c, pre_c;
    ImageView help_image;
    TextView help_text, help_content;
    int i = 0;
    static Activity activity;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.imageView2) ImageView iv;


    @OnClick(R.id.help) void Help(View v){
        showHelpPopup();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_home);
        ButterKnife.bind(this);

        activity = this;

        if(isOnline()==false){
            Toast.makeText(HomeActivity.this, "인터넷을 연결해주세요!", Toast.LENGTH_SHORT).show();
        }

        DBHelper dbHelper = new DBHelper(getApplicationContext(), "CHECK.db", null, 1);
        Log.v("모야모야모야모야",""+dbHelper.rtnCheck());
        if(dbHelper.rtnCheck()==0){
            dbHelper.insert();
            Log.v("넣었다데스","1");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showHelpPopup();
                }
            },100);
        }else{
            Log.v("쟌넨","1");
        }


//        Log.v("dbhelper떴냥", ""+dbHelper.getResult2());



        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
//                Toast.makeText(HomeActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                Toast.makeText(HomeActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
                .check();


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

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i>5){
                    Toast.makeText(getApplicationContext(), "유정쨩카와이이이이이이익", Toast.LENGTH_SHORT).show();
                    i = 0;
                }
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);   //toolbar title 삭제

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {    //뒤로가기 버튼 두 번 누르면 종료
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)  //연속 누를 때 2초 안에 안누르면 종료 x
            {
                super.onBackPressed();
            }
            else    //종료
            {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            LayoutInflater inflater = (LayoutInflater) HomeActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, mWidthPixels-200, mHeightPixels-1000, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            pwindo.setOutsideTouchable(true);
            pwindo.setBackgroundDrawable(new BitmapDrawable());
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
            intent = new Intent(getApplicationContext(),SupportActivity_Fix.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_success) {
            intent = new Intent(getApplicationContext(),SuccessActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void NoticeBT(View v){
        Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
        startActivity(intent);
        finish();
    }

    public void RegionBT(View v){
        Intent intent = new Intent(getApplicationContext(), RegionActivity.class);
        startActivity(intent);
        finish();

    }

    public void SupportBT(View v){
        Intent intent = new Intent(getApplicationContext(), SupportActivity_Fix.class);
        startActivity(intent);
        finish();
    }

    public void SuccessBT(View v){
        Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
        startActivity(intent);
        finish();
    }

    public void showHelpPopup(){
        LayoutInflater inflater = (LayoutInflater) HomeActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_explain,
                (ViewGroup) findViewById(R.id.popup_element));
        helpwindo = new PopupWindow(layout, mWidthPixels-200, mHeightPixels-500, true);
        helpwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        helpwindo.setOutsideTouchable(true);
        helpwindo.setBackgroundDrawable(new BitmapDrawable());

        next_c = (CardView) layout.findViewById(R.id.explain_next_c);
        pre_c = (CardView) layout.findViewById(R.id.explain_pre_c);
        help_image = (ImageView) layout.findViewById(R.id.help_image);
        help_text = (TextView) layout.findViewById(R.id.help_text);
        help_content = (TextView) layout.findViewById(R.id.help_content);
        close = (ImageButton) layout.findViewById(R.id.help_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpwindo.dismiss();
            }
        });


        next = (Button) layout.findViewById(R.id.explain_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable noww = help_image.getDrawable();
                Drawable temp = getResources().getDrawable(R.drawable.megaphone);
                Drawable temp1 = getResources().getDrawable(R.drawable.region);
                Drawable temp2 = getResources().getDrawable(R.drawable.list);
                Drawable temp3 = getResources().getDrawable(R.drawable.medal);

                Bitmap now = ((BitmapDrawable)noww).getBitmap();
                Bitmap megaphone = ((BitmapDrawable)temp).getBitmap();
                Bitmap region = ((BitmapDrawable)temp1).getBitmap();
                Bitmap list = ((BitmapDrawable)temp2).getBitmap();
                Bitmap medal = ((BitmapDrawable)temp3).getBitmap();

                if (now.equals(megaphone)) {
                    pre_c.setVisibility(View.VISIBLE);
                    help_image.setImageResource(R.drawable.region);
                    help_text.setText("지역");
                    help_content.setText("귀농 할 수 있는 마을과 해당마을 주소, 그리고 인근 마을 시설에 대해 알려드립니다.");
                } else if (now.equals(region)) {
                    help_image.setImageResource(R.drawable.list);
                    help_text.setText("지원사업");
                    help_content.setText("해당 마을에서 진행하고 있는 지자체 지원사업에 대해 알려드립니다.");
                } else if (now.equals(list)) {
                    next.setText("닫기");
                    help_image.setImageResource(R.drawable.medal);
                    help_text.setText("성공사례");
                    help_content.setText("귀농에 성공하신 분들의 이야기를 들려드립니다.");
                } else if(now.equals(medal)){
                    helpwindo.dismiss();
                }
            }
        });
        pre = (Button) layout.findViewById(R.id.explain_pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable noww = help_image.getDrawable();
                Drawable temp1 = getResources().getDrawable(R.drawable.region);
                Drawable temp2 = getResources().getDrawable(R.drawable.list);
                Drawable temp3 = getResources().getDrawable(R.drawable.medal);

                Bitmap now = ((BitmapDrawable)noww).getBitmap();
                Bitmap region = ((BitmapDrawable)temp1).getBitmap();
                Bitmap list = ((BitmapDrawable)temp2).getBitmap();
                Bitmap medal = ((BitmapDrawable)temp3).getBitmap();
                if(now.equals(region)){
                    pre_c.setVisibility(View.INVISIBLE);
                    help_image.setImageResource(R.drawable.megaphone);
                    help_text.setText("공지사항");
                    help_content.setText("공지사항을 알려드립니다.");
                }else if(now.equals(list)){
                    help_image.setImageResource(R.drawable.region);
                    help_text.setText("지역");
                    help_content.setText("귀농 할 수 있는 마을과 해당마을 주소, 그리고 인근 마을 시설에 대해 알려드립니다.");
                }else if(now.equals(medal)){
                    next.setText("다음");
                    help_image.setImageResource(R.drawable.list);
                    help_text.setText("지원사업");
                    help_content.setText("해당 마을에서 진행하고 있는 지자체지원사업에 대해 알려드립니다.");
                }
            }
        });
    }

    private static boolean isOnline() { // network 연결 상태 확인
        try {
            ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState(); // wifi
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                return true;
            }
            NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState(); // mobile ConnectivityManager.TYPE_MOBILE
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}