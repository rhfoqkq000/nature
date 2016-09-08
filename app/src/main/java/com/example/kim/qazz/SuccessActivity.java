package com.example.kim.qazz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SuccessActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;
    static HashMap<String, String> dbArticle = new HashMap<String, String>();
    Handler handler = new Handler();
    String urlStr1, loadHtmlStr = "";
    ArrayList<String> dbTitle = new ArrayList<String>();
    ProgressDialog mProgressDialog;

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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showProgressDialog();

        urlStr1 = "http://returntocs.xyz/suex";
        loadHtml(urlStr1);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dbTitle);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Set key = dbArticle.keySet();
                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                    String keyName = (String) iterator.next();
                    dbTitle.add(keyName);
                }
//                mlist = (ListView)findViewById(R.id.success_list);
//                mlist.setAdapter(adapter);
                mlist.setAdapter(adapter);
                mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String gt = (""+parent.getItemAtPosition(position));
                        Intent intent = new Intent(getBaseContext(), ArticleActivity.class);
                        intent.putExtra("dbTitle",(String)parent.getItemAtPosition(position));
                        intent.putExtra("dbArticle",dbArticle);
                        startActivity(intent);
                        finish();
                    }
                });
        hideProgressDialog();
            }
        }, 1000);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public String loadHtml(final String url1) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuffer sb = new StringBuffer();
                try {
                    URL url = new URL(url1);
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();// 접속
                    if (conn != null) {
                        conn.setConnectTimeout(2000);
                        conn.setUseCaches(false);
                        if (conn.getResponseCode()
                                == HttpURLConnection.HTTP_OK) {
                            BufferedReader br
                                    = new BufferedReader(new InputStreamReader
                                    (conn.getInputStream(), "utf-8"));//"utf-8"
                            while (true) {
                                String line = br.readLine();
                                if (line == null) break;
                                sb.append(line + "\n");
                            }
                            loadHtmlStr=sb.toString();
                            JSONArray jArray = new JSONArray(loadHtmlStr);

                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                String title = jObject.getString("title");
                                String content = jObject.getString("contents");
                                dbArticle.put(title, content);
                            }

                            br.close(); // 스트림 해제
                        }
                        conn.disconnect(); // 연결 끊기
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); // 쓰레드 시작
        return loadHtmlStr;
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