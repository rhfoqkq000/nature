package com.example.kim.qazz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {
//    String urlStr1 = "";
//    Handler handler = new Handler();
//    String strJson = "";
//    String source = "";
    ProgressDialog mProgressDialog;

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvDetail) TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_article);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String dbTitle = intent.getExtras().getString("dbTitle");
        HashMap<String, String> dbArticle = (HashMap<String, String>) intent.getSerializableExtra("dbArticle");
        tvTitle.setText(dbTitle);
        tvDetail.setText(dbArticle.get(dbTitle));
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(getApplicationContext(), "Waiting...", "Please wait five seconds...");
        }
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

}
