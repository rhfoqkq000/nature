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
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

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

/**
 * Created by user on 2016-07-29.
 */
//주석
public class HouseActivity extends AppCompatActivity {
    ArrayList<String> sido_cd, rkddnjs, rudska, rudqnr, wjsska, wjsqnr, cndska, cndqnr;
    Handler handler = new Handler();
    String urlStr1 = "http://133.130.100.142:10001/helpHouse/";
    String urlStr2, urlStr3, getStr1, getStr2 = "";
    private GoogleApiClient client;
    View mv;
    Button btnSend2;
    TextView houseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_house);

        houseText = (TextView)findViewById(R.id.HouseText);
        sido_cd = new ArrayList<String>();
        sido_cd.add("강원도");
        sido_cd.add("경상남도");
        sido_cd.add("경상북도");
        sido_cd.add("전라남도");
        sido_cd.add("전라북도");
        sido_cd.add("충청남도");
        sido_cd.add("충청북도");

        rkddnjs = new ArrayList<String>();
        rkddnjs.add("양양군");
        rkddnjs.add("인제군");

        rudska = new ArrayList<String>();
        rudska.add("거창군");
        rudska.add("산청군");
        rudska.add("의령군");
        rudska.add("창녕군");
        rudska.add("함양군");
        rudska.add("합천군");

        rudqnr = new ArrayList<String>();
        rudqnr.add("문경시");
        rudqnr.add("봉화군");
        rudqnr.add("상주시");
        rudqnr.add("영덕군");
        rudqnr.add("예천군");
        rudqnr.add("울진군");
        rudqnr.add("의성군");

        wjsska = new ArrayList<String>();
        wjsska.add("강진군");
        wjsska.add("고흥군");
        wjsska.add("곡성군");
        wjsska.add("구례군");
        wjsska.add("나주시");
        wjsska.add("순천시");
        wjsska.add("신안군");
        wjsska.add("여수시");
        wjsska.add("영광군");
        wjsska.add("영암군");
        wjsska.add("장성군");
        wjsska.add("진도군");
        wjsska.add("함평군");
        wjsska.add("해남군");
        wjsska.add("화순군");

        wjsqnr = new ArrayList<String>();
        wjsqnr.add("고창군");
        wjsqnr.add("김제시");
        wjsqnr.add("남원시");
        wjsqnr.add("무주군");
        wjsqnr.add("부안군");
        wjsqnr.add("순창군");
        wjsqnr.add("완주군");
        wjsqnr.add("장수군");
        wjsqnr.add("정읍시");
        wjsqnr.add("진안군");

        cndska = new ArrayList<String>();
        cndska.add("금산군");
        cndska.add("부여군");
        cndska.add("서천군");
        cndska.add("아산시");
        cndska.add("청양군");
        cndska.add("홍성군");

        cndqnr = new ArrayList<String>();
        cndqnr.add("괴산군");
        cndqnr.add("단양군");
        cndqnr.add("보은군");
        cndqnr.add("영동군");
        cndqnr.add("증평군");
        cndqnr.add("충주시");

//        mv = getLayoutInflater().inflate(R.layout.activity_house, null);

        Spinner ahspinner1 = (Spinner)findViewById(R.id.ahspinner1);
        final Spinner ahspinner2 = (Spinner)findViewById(R.id.ahspinner2);
//        Toast.makeText(getApplicationContext(),"찾았냐",Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sido_cd);
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rkddnjs);
        final ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rudska);
        final ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rudqnr);
        final ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wjsska);
        final ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wjsqnr);
        final ArrayAdapter adapter7 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cndska);
        final ArrayAdapter adapter8 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cndqnr);

        ahspinner1.setAdapter(adapter);
//                        Toast.makeText(getApplicationContext(),"어댑터떴냐",Toast.LENGTH_SHORT).show();

        ahspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getStr1 = (""+parent.getItemAtPosition(position));
//                Toast.makeText(getApplicationContext(),"선택했냐",Toast.LENGTH_SHORT).show();
                switch(getStr1){
                    case "강원도":
                        ahspinner2.setAdapter(adapter2);
                        break;
                    case "경상남도":
                        ahspinner2.setAdapter(adapter3);
                        break;
                    case "경상북도":
                        ahspinner2.setAdapter(adapter4);
                        break;
                    case "전라남도":
                        ahspinner2.setAdapter(adapter5);
                        break;
                    case "전라북도":
                        ahspinner2.setAdapter(adapter6);
                        break;
                    case "충청남도":
                        ahspinner2.setAdapter(adapter7);
                        break;
                    case "충청북도":
                        ahspinner2.setAdapter(adapter8);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ahspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getStr2 = (""+parent.getItemAtPosition(position));
                urlStr1 = "http://133.130.100.142:10001/helpHouse/"+getStr1+"&"+getStr2;
                Toast.makeText(getApplicationContext(),""+urlStr1, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSend2 = (Button)findViewById(R.id.btn_send2);
        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHtml();
                final Handler mHandler = new Handler();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String strJson = houseText.getText().toString();
//                        Toast.makeText(getApplicationContext(),"스트링"+strJson,Toast.LENGTH_SHORT).show();
                        StringBuffer sb2 = new StringBuffer();
                        try {
                            JSONArray jArray = new JSONArray(strJson);

                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                String sido = jObject.getString("sido");
                                String sigun = jObject.getString("sigun");
                                String name = jObject.getString("name");
                                String scale = jObject.getString("scale");
                                String fee = jObject.getString("fee");
                                String period = jObject.getString("period");
//                                String day = jObject.getString("day");
                                String tel = jObject.getString("tel");
                                sb2.append(
                                        "시도 : "+sido+"\n"+"시군 : "+sigun+"\n"+"이름 : "+name+"\n"+"임대료 : "+fee+"\n"+"기간 : "+period+"\n"+"전화번호 : "+tel+"\n\n"
                                );
//                                Toast.makeText(getApplicationContext(),"sb"+sb2.toString(),Toast.LENGTH_SHORT).show();

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        houseText.setText(sb2.toString());
                    }
                }, 1000);


            }
        });

    }

    void loadHtml() {
        Thread t = new Thread(new Runnable() {
            //            houseText = (TextView)findViewById(R.id.HouseText);
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
                    Log.d("test", sb.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            houseText.setText(sb.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); // 쓰레드 시작

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_house);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
//        }
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
}