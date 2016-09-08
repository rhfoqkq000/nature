package com.example.kim.qazz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import java.util.ArrayList;
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
        db.execSQL("CREATE TABLE SPINNER (id INTEGER, name TEXT, code INTEGER);");
        db.execSQL("CREATE TABLE CHECK (check INTEGER);");

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO CHECK VALUES(0);");

        db.execSQL("INSERT INTO SPINNER VALUES(101, 강원도, 6420000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 경기도, 6410000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 경상남도, 6480000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 경상북도, 6470000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 서울특별시, 6110000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 전라남도, 6460000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 전라북도, 6450000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 충청남도, 6440000);");
        db.execSQL("INSERT INTO SPINNER VALUES(101, 충청북도, 6430000);");

        db.execSQL("INSERT INTO SPINNER VALUES(102, 양양군, 4350000);");
        db.execSQL("INSERT INTO SPINNER VALUES(102, 인제군, 4330000);");

        db.execSQL("INSERT INTO SPINNER VALUES(103, 거창군, 5470000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 산청군, 5450000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 의령군, 5390000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 진주시, 5310000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 창녕군, 5410000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 함양군, 5460000);");
        db.execSQL("INSERT INTO SPINNER VALUES(103, 합천군, 5480000);");

        db.execSQL("INSERT INTO SPINNER VALUES(104, 문경시, 5120000);");
        db.execSQL("INSERT INTO SPINNER VALUES(104, 봉화군, 5240000);");
        db.execSQL("INSERT INTO SPINNER VALUES(104, 상주시, 5110000);");
        db.execSQL("INSERT INTO SPINNER VALUES(104, 예천군, 5230000);");
        db.execSQL("INSERT INTO SPINNER VALUES(104, 울진군, 5250000);");
        db.execSQL("INSERT INTO SPINNER VALUES(104, 의성군, 5150000);");

        db.execSQL("INSERT INTO SPINNER VALUES(105, 강진군, 4920000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 고흥군, 4880000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 곡성군, 4860000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 구례군, 4870000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 나주시, 4830000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 순천시, 4820000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 신안군, 5010000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 여수시, 4810000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 영광군, 4970000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 영암군, 4940000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 장성군, 4980000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 진도군, 5000000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 함평군, 4960000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 해남군, 4930000);");
        db.execSQL("INSERT INTO SPINNER VALUES(105, 화순군, 4900000);");

        db.execSQL("INSERT INTO SPINNER VALUES(106, 고창군, 4780000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 김제시, 4710000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 남원시, 4700000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 무주군, 4740000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 부안군, 4790000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 순창군, 4770000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 완주군, 4720000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 장수군, 4750000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 정읍시, 4690000);");
        db.execSQL("INSERT INTO SPINNER VALUES(106, 진안군, 4730000);");

        db.execSQL("INSERT INTO SPINNER VALUES(107, 금산군, 4550000);");
        db.execSQL("INSERT INTO SPINNER VALUES(107, 부여군, 4570000);");
        db.execSQL("INSERT INTO SPINNER VALUES(107, 서천군, 4580000);");
        db.execSQL("INSERT INTO SPINNER VALUES(107, 아산시, 4520000);");
        db.execSQL("INSERT INTO SPINNER VALUES(107, 청양군, 4590000);");
        db.execSQL("INSERT INTO SPINNER VALUES(107, 홍성군, 4600000);");

        db.execSQL("INSERT INTO SPINNER VALUES(108, 괴산군, 4460000);");
        db.execSQL("INSERT INTO SPINNER VALUES(108, 단양군, 4480000);");
        db.execSQL("INSERT INTO SPINNER VALUES(108, 보은군, 4420000);");
        db.execSQL("INSERT INTO SPINNER VALUES(108, 영동군, 4440000);");
        db.execSQL("INSERT INTO SPINNER VALUES(108, 증평군, 5570000);");
        db.execSQL("INSERT INTO SPINNER VALUES(108, 충주시, 4390000);");

        db.execSQL("INSERT INTO SPINNER VALUES(109, 구리시, 3980000);");
        db.execSQL("INSERT INTO SPINNER VALUES(109, 연천군, 4140000);");
        db.execSQL("INSERT INTO SPINNER VALUES(109, 포천시, 5600000);");

        db.execSQL("INSERT INTO SPINNER VALUES(110, 양양군, 4350000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 양양군, 4350000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 인제군, 4330000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 거창군, 5470000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 산청군, 5450000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 의령군, 5390000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 진주시, 5310000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 창녕군, 5410000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 함양군, 5460000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 합천군, 5480000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 문경시, 5120000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 봉화군, 5240000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 상주시, 5110000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 예천군, 5230000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 울진군, 5250000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 의성군, 5150000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 강진군, 4920000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 고흥군, 4880000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 곡성군, 4860000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 구례군, 4870000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 나주시, 4830000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 순천시, 4820000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 신안군, 5010000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 여수시, 4810000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 영광군, 4970000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 영암군, 4940000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 장성군, 4980000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 진도군, 5000000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 함평군, 4960000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 해남군, 4930000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 화순군, 4900000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 고창군, 4780000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 김제시, 4710000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 남원시, 4700000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 무주군, 4740000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 부안군, 4790000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 순창군, 4770000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 완주군, 4720000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 장수군, 4750000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 정읍시, 4690000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 진안군, 4730000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 금산군, 4550000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 부여군, 4570000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 서천군, 4580000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 아산시, 4520000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 청양군, 4590000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 홍성군, 4600000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 괴산군, 4460000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 단양군, 4480000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 보은군, 4420000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 영동군, 4440000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 증평군, 5570000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 충주시, 4390000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 구리시, 3980000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 연천군, 4140000);");
        db.execSQL("INSERT INTO SPINNER VALUES(110, 포천시, 5600000);");

        db.close();
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

    public ArrayList<HashMap<ArrayList<String>, ArrayList<Integer>>> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<HashMap<ArrayList<String>, ArrayList<Integer>>> result = new ArrayList<HashMap<ArrayList<String>, ArrayList<Integer>>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash1 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash2 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash3 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash4 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash5 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash6 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash7 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash8 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash9 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        HashMap<ArrayList<String>, ArrayList<Integer>> hash10 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        ArrayList<String> name1 = new ArrayList<String>();
        ArrayList<String> name2 = new ArrayList<String>();
        ArrayList<String> name3 = new ArrayList<String>();
        ArrayList<String> name4 = new ArrayList<String>();
        ArrayList<String> name5 = new ArrayList<String>();
        ArrayList<String> name6 = new ArrayList<String>();
        ArrayList<String> name7 = new ArrayList<String>();
        ArrayList<String> name8 = new ArrayList<String>();
        ArrayList<String> name9 = new ArrayList<String>();
        ArrayList<String> name10 = new ArrayList<String>();
        ArrayList<Integer> code1 = new ArrayList<Integer>();
        ArrayList<Integer> code2 = new ArrayList<Integer>();
        ArrayList<Integer> code3 = new ArrayList<Integer>();
        ArrayList<Integer> code4 = new ArrayList<Integer>();
        ArrayList<Integer> code5 = new ArrayList<Integer>();
        ArrayList<Integer> code6 = new ArrayList<Integer>();
        ArrayList<Integer> code7 = new ArrayList<Integer>();
        ArrayList<Integer> code8 = new ArrayList<Integer>();
        ArrayList<Integer> code9 = new ArrayList<Integer>();
        ArrayList<Integer> code10 = new ArrayList<Integer>();

        Cursor cursor = db.rawQuery("SELECT * FROM SPINNER", null);
        for(int i = 0; i<cursor.getColumnCount(); i++){
            switch(cursor.getInt(0)){
                case 101:
                    name1.add(i, cursor.getString(1));
                    code1.add(i, cursor.getInt(2));
                    break;
                case 102:
                    name2.add(i, cursor.getString(1));
                    code2.add(i, cursor.getInt(2));
                    break;
                case 103:
                    name3.add(i, cursor.getString(1));
                    code3.add(i, cursor.getInt(2));
                    break;
                case 104:
                    name4.add(i, cursor.getString(1));
                    code4.add(i, cursor.getInt(2));
                    break;
                case 105:
                    name5.add(i, cursor.getString(1));
                    code5.add(i, cursor.getInt(2));
                    break;
                case 106:
                    name6.add(i, cursor.getString(1));
                    code6.add(i, cursor.getInt(2));
                    break;
                case 107:
                    name7.add(i, cursor.getString(1));
                    code7.add(i, cursor.getInt(2));
                    break;
                case 108:
                    name8.add(i, cursor.getString(1));
                    code8.add(i, cursor.getInt(2));
                    break;
                case 109:
                    name9.add(i, cursor.getString(1));
                    code9.add(i, cursor.getInt(2));
                    break;
                case 110:
                    name10.add(i, cursor.getString(1));
                    code10.add(i, cursor.getInt(2));
                    break;
            }
        }
        hash1.put(name1, code1);
        hash2.put(name2, code2);
        hash3.put(name3, code3);
        hash4.put(name4, code4);
        hash5.put(name5, code5);
        hash6.put(name6, code6);
        hash7.put(name7, code7);
        hash8.put(name8, code8);
        hash9.put(name9, code9);
        hash10.put(name10, code10);

        return result;
    }



    public HashMap<ArrayList<String>, ArrayList<Integer>> getResult2(Integer param) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> code = new ArrayList<Integer>();

        HashMap<ArrayList<String>, ArrayList<Integer>> result22 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        Cursor cursor = db.rawQuery("SELECT * FROM SPINNER WHERE id="+param, null);
        for(int i = 0; i<cursor.getColumnCount(); i++){
            name.add(i, cursor.getString(1));
            code.add(i, cursor.getInt(2));
        }
        result22.put(name, code);
        return result22;
    }


}