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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

public class ArticleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String urlStr1 = "";
    Handler handler = new Handler();
    String strJson = "";
    String source = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout5);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view5);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvDetail = (TextView) findViewById(R.id.tvDetail);

        Intent intent = getIntent();
        String dbTitle = intent.getExtras().getString("dbTitle");
        HashMap<String, String> dbArticle = (HashMap<String, String>) intent.getSerializableExtra("dbArticle");
//        Toast.makeText(getApplicationContext(),""+dbArticle, Toast.LENGTH_SHORT).show();
        tvTitle.setText(dbTitle);
        tvDetail.setText(dbArticle.get(dbTitle));
//        source = dbArticle.get(dbTitle);
//
//        Parser parser = Parser.builder().build();
//        Node document = parser.parse(source);
//        HtmlRenderer renderer = HtmlRenderer.builder().build();
//        renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
//        //__버섯__->html
//        Log.i("렌더떳어?",""+renderer.render(document));
//        source = renderer.render(document);
//
//        htt();

    }

    public void htt() {
//    String source = "<b><font color=#ff0000> Html View using TextView" +
//            "</font></b><br><br><a href='http://m.naver.com'>naver.com</a>" +
//            "<br><br><a href='http://mainia.tistory.com'>mainia.tistory.com</a>";

//    TextView tvDetail = (TextView) findViewById(R.id.tvDetail);
//    tvDetail.setText(Html.fromHtml(source));
//        Log.i("tvDetail떴떴냐",""+Html.fromHtml(source));
//        WebView web = (WebView) findViewById(R.id.webapp);
//// 자바스크립트 허용
//        web.getSettings().setJavaScriptEnabled(true);

//        String source = "<b><font color=#ff0000> Html View using TextView" +
//                "</font></b><br><br><a href='http://m.naver.com'>naver.com</a>" +
//                "<br><br><a href='http://mainia.tistory.com'>mainia.tistory.com</a>";
//        web.loadData(source, "text/html", "UTF-8");
    }


    @Override
    public void onBackPressed() {
            Intent intent = new Intent(getBaseContext(), SuccessActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout5);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
