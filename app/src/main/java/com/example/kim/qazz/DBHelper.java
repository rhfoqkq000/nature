package com.example.kim.qazz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    Handler handler = new Handler();

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
//        db.execSQL("CREATE TABLE MONEYBOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, price INTEGER, create_at TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String create_at, String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
//        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
//        db.execSQL("INSERT INTO MONEYBOOK VALUES(null, '" + item + "', " + price + ", '" + create_at + "');");
//        db.close();
    }

    public void update(String item, int price) {
//        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
//        db.close();
    }

    public void delete(String item) {
//        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
//        db.execSQL("DELETE FROM MONEYBOOK WHERE item='" + item + "';");
//        db.close();
    }

    public HashMap<String, String> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
//        String result = "";
//        ArrayList<String> result22 = new ArrayList<String>();
        HashMap<String, String> result22 = new HashMap<String, String>();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ARTICLE", null);
       while (cursor.moveToNext()) {
          // result += cursor.getString(1)+"\n\n\n";
           result22.put(cursor.getString(1), cursor.getString(2));
        }
//        loadHtml();


        return result22;
    }

//
//    void loadHtml() {
//        Thread t = new Thread(new Runnable() {
////            TextView tv3 = (TextView) findViewById(R.id.textView2);
//
//            @Override
//            public void run() {
//                final StringBuffer sb = new StringBuffer();
//
//                try {
//                    URL url = new URL("http://returntocs.xyz/suex");
//                    HttpURLConnection conn =
//                            (HttpURLConnection) url.openConnection();// 접속
//                    if (conn != null) {
//                        conn.setConnectTimeout(2000);
//                        conn.setUseCaches(false);
//                        if (conn.getResponseCode()
//                                == HttpURLConnection.HTTP_OK) {
//                            //    데이터 읽기
//                            BufferedReader br
//                                    = new BufferedReader(new InputStreamReader
//                                    (conn.getInputStream(), "utf-8"));//"utf-8"
//                            while (true) {
//                                String line = br.readLine();
//                                if (line == null) break;
//                                sb.append(line + "\n");
//                            }
//                            br.close(); // 스트림 해제
//                        }
//                        conn.disconnect(); // 연결 끊기
//                    }
//                    // 값을 출력하기
//                    Log.d("test떴냐", sb.toString());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                            tv3.setText(sb.toString());
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start(); // 쓰레드 시작
//    }

}