package com.example.kim.qazz;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kim.qazz.Retrofit.HelpHouse;
import com.example.kim.qazz.Retrofit.Repo;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-09-07.
 */
public class ReturnHomeFragment extends Fragment {
    ArrayList<String> sido_cd, rkddnjs, rudska, rudqnr, wjsska, wjsqnr, cndska, cndqnr;
    Handler handler = new Handler();
    String urlStr1 = "http://returntocs.xyz/helpHouse/";
    String urlStr2, urlStr3, getStr1, getStr2 = "";
    private GoogleApiClient client;
    View mv;
    Button btnSend2;
    TextView houseText;

    public ReturnHomeFragment() {
    }

    //PlaceholderFragment.newInstance()와 똑같이 추가
    static ReturnHomeFragment newInstance(int SectionNumber) {
        ReturnHomeFragment fragment = new ReturnHomeFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.content_house, container, false);

        houseText = (TextView) rootview.findViewById(R.id.HouseText);
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

        Spinner ahspinner1 = (Spinner) rootview.findViewById(R.id.ahspinner1);
        final Spinner ahspinner2 = (Spinner) rootview.findViewById(R.id.ahspinner2);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, sido_cd);
        final ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, rkddnjs);
        final ArrayAdapter adapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, rudska);
        final ArrayAdapter adapter4 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, rudqnr);
        final ArrayAdapter adapter5 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, wjsska);
        final ArrayAdapter adapter6 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, wjsqnr);
        final ArrayAdapter adapter7 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cndska);
        final ArrayAdapter adapter8 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cndqnr);

        ahspinner1.setAdapter(adapter);
        ahspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getStr1 = (""+parent.getItemAtPosition(position));
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
//                urlStr1 = "http://returntocs.xyz/helpHouse/"+getStr1+"&"+getStr2;
                urlStr1 = "http://returntocs.xyz/helpHouse/";

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSend2 = (Button) rootview.findViewById(R.id.btn_send2);
        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit client = new Retrofit.Builder().baseUrl(urlStr1)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                HelpHouse helpHouse = client.create(HelpHouse.class);
                final Call<Repo> call = helpHouse.repo();
                call.enqueue(new Callback<Repo>() {
                    @Override
                    public void onResponse(Call<Repo> call, Response<Repo> response) {
                        if (response.isSuccessful()) {
                            Repo repo = response.body();
                            Log.i("REPO:9",""+repo.getFee());
                        }
                    }

                    @Override
                    public void onFailure(Call<Repo> call, Throwable t) {
                        Log.e("REPO:9","싯빠이요");
                    }
                });
            }
        });

        return rootview;
    }
}