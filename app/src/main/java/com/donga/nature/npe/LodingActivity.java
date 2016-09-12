package com.donga.nature.npe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by nature on 16. 7. 29.
 */
public class LodingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_view);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1800);
    }

    private class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(), HomeActivity.class));
            LodingActivity.this.finish();
        }
    }
}
