package com.example.kim.qazz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.kim.qazz.RetrofitSuccess.Repo;
import com.example.kim.qazz.RetrofitSuccess.Success;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SuccessActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;
    static HashMap<String, String> dbArticle = new HashMap<String, String>();
    Handler handler = new Handler();
    String urlStr1, loadHtmlStr = "";
    ArrayList<String> dbTitle = new ArrayList<String>();
    ProgressDialog mProgressDialog;
    Context mContext;
    int mWidthPixels, mHeightPixels;
    PopupWindow pwindo;
    Button btnClosePopup;

    @BindView(R.id.toolbar4) Toolbar toolbar;
    @BindView(R.id.drawer_layout4) DrawerLayout drawer;
    @BindView(R.id.nav_view4) NavigationView navigationView;
    @BindView(R.id.success_list) ListView mlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_success);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mContext = this;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


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



        navigationView.setNavigationItemSelectedListener(this);

        showProgressDialog();

        urlStr1 = "http://45.32.61.201:3000/nature/";
//        loadHtml(urlStr1);
        Retrofit client = new Retrofit.Builder().baseUrl(urlStr1)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Success success = client.create(Success.class);
        final Call<Repo> call = success.repo();

        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                final Success_ListViewAdapter ladapter = new Success_ListViewAdapter();
                if (response.isSuccessful()) {
                    Repo repo = response.body();
                    for(int i = 0; i<repo.getResult_data().size(); i++){
                        dbTitle.add(i, repo.getResult_data().get(i).getTitle());
                        ladapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.village), repo.getResult_data().get(i).getTitle());
                        dbArticle.put(repo.getResult_data().get(i).getTitle(), repo.getResult_data().get(i).getContents());
                    }
//                    final ArrayAdapter adapter =
//                            new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, dbTitle);
                    hideProgressDialog();

//                    mlist.setAdapter(adapter);
                    mlist.setAdapter(ladapter);
                    mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String gt = (""+parent.getItemAtPosition(position));
                            Intent intent = new Intent(getBaseContext(), ArticleActivity.class);
//                            intent.putExtra("dbTitle", parent.getItemAtPosition(position).toString());
                            HashMap<String, Object> obj = (HashMap<String, Object>) ladapter.getItem(position);
                            String name = (String)obj.get("mtext");
                            intent.putExtra("dbTitle",name);
                            intent.putExtra("dbArticle",dbArticle);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
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
            LayoutInflater inflater = (LayoutInflater) SuccessActivity.this
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
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(SuccessActivity.this, "로딩중...", "잠시만 기다려주세요.");
        }
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}