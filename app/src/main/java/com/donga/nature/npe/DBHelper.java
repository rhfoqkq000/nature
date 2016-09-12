package com.donga.nature.npe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

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
        db.execSQL("CREATE TABLE check_dialog (ch_tf INTEGER);");

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert() {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO check_dialog VALUES(1212);");
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

    public Integer getResult2() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

//        ArrayList<String> name = new ArrayList<String>();
//        ArrayList<Integer> code = new ArrayList<Integer>();

//        HashMap<ArrayList<String>, ArrayList<Integer>> result22 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        Cursor cursor = db.rawQuery("SELECT * FROM check_dialog WHERE ch_tf=1212", null);
//        for(int i = 0; i<cursor.getColumnCount(); i++){
//            name.add(i, cursor.getString(1));
//            code.add(i, cursor.getInt(2));
//        }
        int dbcheck = cursor.getInt(0);
        return dbcheck;
    }

    public Integer rtnCheck(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM check_dialog", null);
        return cursor.getCount();
    }


}