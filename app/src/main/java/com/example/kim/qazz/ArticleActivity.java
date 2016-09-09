package com.example.kim.qazz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {
    int mWidthPixels, mHeightPixels;
    PopupWindow pwindo;
    Button btnClosePopup;

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), SuccessActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();

    }

}
