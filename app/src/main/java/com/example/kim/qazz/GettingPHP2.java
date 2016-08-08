package com.example.kim.qazz;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**

 * Created by Ryu on 2016-07-28.

 */

public class GettingPHP2 extends AsyncTask<String, String, String> {
    public static ArrayList<String> address = new ArrayList<>();

    public static ArrayList<Double> longitude = new ArrayList<>();

    public static ArrayList<Double> latitude = new ArrayList<>();

    public static ArrayList<String> townName = new ArrayList<>();

    public static ArrayList<String> townFact = new ArrayList<>();

    public static ArrayList<String> townGood = new ArrayList<>();

    public static ArrayList<String> townBad = new ArrayList<>();


//    static ArrayList<String> add = new ArrayList<>();
    String addSt;

    @Override
    protected String doInBackground(String... params) {
        StringBuilder jsonHtml = new StringBuilder();
        try {
            URL phpUrl = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        jsonHtml.append(line + "\n");
                    }
//                    Log.i("jsonHtml.toString() 컴온", jsonHtml.toString());
                    br.close();
                }
                conn.disconnect(); //메모리누수방지
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonHtml.toString();
    }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONArray jArray = new JSONArray(str);
                JSONObject jObject;
                address.clear();
                longitude.clear();
                latitude.clear();
                townName.clear();
                townFact.clear();
                townGood.clear();
                townBad.clear();
                addSt = null;
                for (int i = 0; i < jArray.length(); i++) {
                    jObject = jArray.getJSONObject(i);
                    address.add(i, jObject.getString("address"));
                    longitude.add(i, jObject.getDouble("longitude"));
                    latitude.add(i, jObject.getDouble("latitude"));
                    townName.add(i, jObject.getString("townName"));
                    townFact.add(i, jObject.getString("townFact"));
                    townGood.add(i, jObject.getString("townGood"));
                    townBad.add(i, jObject.getString("townBad"));
                    addSt = address.get(i);
//                add.add(i, addSt);
//                    Log.i("테스트 세번째", "First : " + first + " / Second : " + second);
                }
                Collections.sort(address, String.CASE_INSENSITIVE_ORDER);
            Log.i("두번째 excute 끝났다", "" + address);
//                String zzzz= "" + second2;
//                String zzz= "" + add;
//                String zz = "" + address;
//                tv.setText(zzzz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}