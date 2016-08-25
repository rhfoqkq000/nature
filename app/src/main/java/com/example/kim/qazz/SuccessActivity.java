package com.example.kim.qazz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SuccessActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView mlist;
    private Success_ListViewAdapter madapter;
    private GoogleApiClient client;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    public static int position;
    static HashMap<String, String> dbArticle = new HashMap<String, String>();
    Handler handler = new Handler();
    String urlStr1 = "";
    String strJson = "";
    TextView tvTitle;
    String loadHtmlStr = "";
    ArrayList<String> dbTitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout4);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(this);




        tvTitle = (TextView)findViewById(R.id.tvTitle);
        TextView tvContent = (TextView)findViewById(R.id.tvDetail);

        urlStr1 = "http://returntocs.xyz/suex";
//                TextView tv3 = (TextView) findViewById(R.id.textView2);
//                tv3.setText(urlStr1);
        loadHtml();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                Toast.makeText(getApplicationContext(),"loadHtmlStr떴떴냐2"+loadHtmlStr, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jArray = new JSONArray(loadHtmlStr);

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray.getJSONObject(i);
                        String title = jObject.getString("title");
                        String content = jObject.getString("contents");
                        dbArticle.put(title, content);
//                        Toast.makeText(getApplicationContext(),"dbArticle떳니"+dbArticle,Toast.LENGTH_SHORT).show();

//                        sb2.append(
//                          "시도:"+sido+"\n"+"시군:"+sigun+"\n"
//                        );
//                        Toast.makeText(getApplicationContext(),"sb"+sb2.toString(),Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(),"11111111", Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }


//                tv3.setText(sb2.toString());
//                Toast.makeText(getApplicationContext(),"dbArticle떳니"+dbArticle, Toast.LENGTH_SHORT).show();
            }
        }, 2000);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dbTitle);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(getApplicationContext(),"2222222", Toast.LENGTH_SHORT).show();

                Set key = dbArticle.keySet();
                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                    String keyName = (String) iterator.next();
                    dbTitle.add(keyName);
                }
//                Toast.makeText(getApplicationContext(),"33333333", Toast.LENGTH_SHORT).show();

                mlist = (ListView)findViewById(R.id.success_list);
                mlist.setAdapter(adapter);

                mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String gt = (""+parent.getItemAtPosition(position));
//                dbArticle.get(parent.getItemAtPosition(position));
//                Toast.makeText(getApplicationContext(),""+dbArticle.get(parent.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();





                        Intent intent = new Intent(getBaseContext(), ArticleActivity.class);
                        intent.putExtra("dbTitle",(String)parent.getItemAtPosition(position));
                        intent.putExtra("dbArticle",dbArticle);
//                Toast.makeText(getApplicationContext(),""+(String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 2000);


//        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "crud.db", null, 4);//흑흑씨발이거야이거
////        Toast.makeText(getApplicationContext(),""+dbHelper.getResult(), Toast.LENGTH_SHORT).show();
//        dbArticle = dbHelper.getResult();




//        madapter = new Success_ListViewAdapter();
//        mlist.setAdapter(madapter);
//
//
//        //list의 아이콘와 내용을 적는 곳
//        madapter.addItem("직장과 농사 병행하며 '표고 전업농' 준비해요");
//
//        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                ListData mData = madapter.mlistData.get(i);
////                Toast.makeText(SuccessActivity.this,mData.mtext,Toast.LENGTH_SHORT).show();
//                position = i;
////                Toast.makeText(getApplicationContext(),"떴니"+position,Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getApplicationContext(),Webviiiew.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout4);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout4);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    void loadHtml() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                final StringBuffer sb = new StringBuffer();

                try {
                    URL url = new URL(urlStr1);
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();// 접속
                    if (conn != null) {
                        conn.setConnectTimeout(2000);
                        conn.setUseCaches(false);
                        if (conn.getResponseCode()
                                == HttpURLConnection.HTTP_OK) {
                            //    데이터 읽기
                            BufferedReader br
                                    = new BufferedReader(new InputStreamReader
                                    (conn.getInputStream(), "utf-8"));//"utf-8"
                            while (true) {
                                String line = br.readLine();
                                if (line == null) break;
                                sb.append(line + "\n");
                            }
                            br.close(); // 스트림 해제
                        }
                        conn.disconnect(); // 연결 끊기
                    }
                    // 값을 출력하기
//                    Log.d("test", sb.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadHtmlStr=sb.toString();
                            Log.d("loadHtmlStr떴떴냐", loadHtmlStr);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); // 쓰레드 시작
    }

}



