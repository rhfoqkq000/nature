package com.donga.nature.npe;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**

 * Created by Ryu on 2016-07-28.

 */

public class GettingPHP extends AsyncTask<String, String, String> {

    static ArrayList<String> address = new ArrayList<>();
    static ArrayList<String> add = new ArrayList<>();
    String addSt;
    String first;
    String second;
    public static ArrayList result_List = new ArrayList();
    static ArrayList<String> second2 = new ArrayList<>();

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

//                    Log.i("jsonHtml.toString()가져와2", jsonHtml.toString());
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
            second2.clear();
            addSt = null;
            first = null;
            second = null;
            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                address.add(i, jObject.getString("address"));
                addSt = address.get(i);
                first = addSt.split(" ")[0];
                second = addSt.split(" ")[1];
                second2.add(i, second);
                add.add(i, addSt);
//                    Log.i("테스트 세번째", "First : " + first + " / Second : " + second);
            }

            //중복제거
            HashSet hs = new HashSet(second2);
            Iterator it = hs.iterator();
            result_List.clear();
            while (it.hasNext()) {
                result_List.add(it.next());
            }
            Collections.sort(result_List, String.CASE_INSENSITIVE_ORDER);
//            Log.i("excute 끝났다", "" + result_List);
//                String zzzz= "" + second2;
//                String zzz= "" + add;
//                String zz = "" + address;
//                tv.setText(zzzz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}