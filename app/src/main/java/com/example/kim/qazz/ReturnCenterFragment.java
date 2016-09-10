package com.example.kim.qazz;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by user on 2016-09-07.
 */
public class ReturnCenterFragment extends Fragment {
    ArrayList<String> arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7, arrayList8, arrayList9;
    Handler handler = new Handler();
    String sendM, sendM2, strJson;
    String tvStr, tvStr2, urlStr1 = "";
    LayoutInflater inflater;
    View layout;
    ArrayAdapter<String> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8, adapter9 = null;
    View mv;
    WebView wv;
    Spinner sp, s2 = null;

    public ReturnCenterFragment() {
    }

    //PlaceholderFragment.newInstance()와 똑같이 추가
    static ReturnCenterFragment newInstance(int SectionNumber) {
        ReturnCenterFragment fragment = new ReturnCenterFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", SectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.content_catalog, container, false);
        ButterKnife.bind(getActivity());
        final HashMap<String, String> hash = new HashMap<String, String>();
        hash.put("강원도", "6420000");
        hash.put("경기도", "6410000");
        hash.put("경상남도", "6480000");
        hash.put("경상북도", "6470000");
        hash.put("전라남도", "6460000");
        hash.put("전라북도", "6450000");
        hash.put("충청남도", "6440000");
        hash.put("충청북도", "6430000");

        final HashMap<String, String> hash2 = new HashMap<String, String>();
        hash2.put("양양군", "4350000");
        hash2.put("인제군", "4330000");

        final HashMap<String, String> hash3 = new HashMap<String, String>();
        hash3.put("거창군", "5470000");
        hash3.put("산청군", "5450000");
        hash3.put("의령군", "5390000");
        hash3.put("진주시", "5310000");
        hash3.put("창녕군", "5410000");
        hash3.put("함양군", "5460000");
        hash3.put("합천군", "5480000");

        final HashMap<String, String> hash4 = new HashMap<String, String>();
        hash4.put("문경시", "5120000");
        hash4.put("봉화군", "5240000");
        hash4.put("상주시", "5110000");
        hash4.put("예천군", "5230000");
        hash4.put("울진군", "5250000");
        hash4.put("의성군", "5150000");

        final HashMap<String, String> hash5 = new HashMap<String, String>();
        hash5.put("강진군", "4920000");
        hash5.put("고흥군", "4880000");
        hash5.put("곡성군", "4860000");
        hash5.put("구례군", "4870000");
        hash5.put("나주시", "4830000");
        hash5.put("순천시", "4820000");
        hash5.put("신안군", "5010000");
        hash5.put("여수시", "4810000");
        hash5.put("영광군", "4970000");
        hash5.put("영암군", "4940000");
        hash5.put("장성군", "4980000");
        hash5.put("진도군", "5000000");
        hash5.put("함평군", "4960000");
        hash5.put("해남군", "4930000");
        hash5.put("화순군", "4900000");

        final HashMap<String, String> hash6 = new HashMap<String, String>();
        hash6.put("고창군", "4780000");
        hash6.put("김제시", "4710000");
        hash6.put("남원시", "4700000");
        hash6.put("무주군", "4740000");
        hash6.put("부안군", "4790000");
        hash6.put("순창군", "4770000");
        hash6.put("완주군", "4720000");
        hash6.put("장수군", "4750000");
        hash6.put("정읍시", "4690000");
        hash6.put("진안군", "4730000");

        final HashMap<String, String> hash7 = new HashMap<String, String>();
        hash7.put("금산군", "4550000");
        hash7.put("부여군", "4570000");
        hash7.put("서천군", "4580000");
        hash7.put("아산시", "4520000");
        hash7.put("청양군", "4590000");
        hash7.put("홍성군", "4600000");

        final HashMap<String, String> hash8 = new HashMap<String, String>();
        hash8.put("괴산군", "4460000");
        hash8.put("단양군", "4480000");
        hash8.put("보은군", "4420000");
        hash8.put("영동군", "4440000");
        hash8.put("증평군", "5570000");
        hash8.put("충주시", "4390000");

        final HashMap<String, String> hash9 = new HashMap<String, String>();
        hash9.put("구리시", "3980000");
        hash9.put("연천군", "4140000");
        hash9.put("포천시", "5600000");

        final HashMap<String, String> hash10 = new HashMap<String, String>();
        hash10.put("양양군", "4350000");
        hash10.put("인제군", "4330000");
        hash10.put("거창군", "5470000");
        hash10.put("산청군", "5450000");
        hash10.put("의령군", "5390000");
        hash10.put("진주시", "5310000");
        hash10.put("창녕군", "5410000");
        hash10.put("함양군", "5460000");
        hash10.put("합천군", "5480000");
        hash10.put("문경시", "5120000");
        hash10.put("봉화군", "5240000");
        hash10.put("상주시", "5110000");
        hash10.put("예천군", "5230000");
        hash10.put("울진군", "5250000");
        hash10.put("의성군", "5150000");
        hash10.put("강진군", "4920000");
        hash10.put("고흥군", "4880000");
        hash10.put("곡성군", "4860000");
        hash10.put("구례군", "4870000");
        hash10.put("나주시", "4830000");
        hash10.put("순천시", "4820000");
        hash10.put("신안군", "5010000");
        hash10.put("여수시", "4810000");
        hash10.put("영광군", "4970000");
        hash10.put("영암군", "4940000");
        hash10.put("장성군", "4980000");
        hash10.put("진도군", "5000000");
        hash10.put("함평군", "4960000");
        hash10.put("해남군", "4930000");
        hash10.put("화순군", "4900000");
        hash10.put("고창군", "4780000");
        hash10.put("김제시", "4710000");
        hash10.put("남원시", "4700000");
        hash10.put("무주군", "4740000");
        hash10.put("부안군", "4790000");
        hash10.put("순창군", "4770000");
        hash10.put("완주군", "4720000");
        hash10.put("장수군", "4750000");
        hash10.put("정읍시", "4690000");
        hash10.put("진안군", "4730000");
        hash10.put("금산군", "4550000");
        hash10.put("부여군", "4570000");
        hash10.put("서천군", "4580000");
        hash10.put("아산시", "4520000");
        hash10.put("청양군", "4590000");
        hash10.put("홍성군", "4600000");
        hash10.put("괴산군", "4460000");
        hash10.put("단양군", "4480000");
        hash10.put("보은군", "4420000");
        hash10.put("영동군", "4440000");
        hash10.put("증평군", "5570000");
        hash10.put("충주시", "4390000");
        hash10.put("구리시", "3980000");
        hash10.put("연천군", "4140000");
        hash10.put("포천시", "5600000");

        Set key = hash.keySet();
        arrayList = new ArrayList<String>();
        for (Iterator iterator = key.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList.add(keyName);
        }
        Set key2 = hash2.keySet();
        arrayList2 = new ArrayList<String>();
        for (Iterator iterator = key2.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList2.add(keyName);
        }
        Set key3 = hash3.keySet();
        arrayList3 = new ArrayList<String>();
        for (Iterator iterator = key3.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList3.add(keyName);
        }
        Set key4 = hash4.keySet();
        arrayList4 = new ArrayList<String>();
        for (Iterator iterator = key4.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList4.add(keyName);
        }
        Set key5 = hash5.keySet();
        arrayList5 = new ArrayList<String>();
        for (Iterator iterator = key5.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList5.add(keyName);
        }
        Set key6 = hash6.keySet();
        arrayList6 = new ArrayList<String>();
        for (Iterator iterator = key6.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList6.add(keyName);
        }
        Set key7 = hash7.keySet();
        arrayList7 = new ArrayList<String>();
        for (Iterator iterator = key7.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList7.add(keyName);
        }
        Set key8 = hash8.keySet();
        arrayList8 = new ArrayList<String>();
        for (Iterator iterator = key8.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList8.add(keyName);
        }
        Set key9 = hash9.keySet();
        arrayList9 = new ArrayList<String>();
        for (Iterator iterator = key9.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            arrayList9.add(keyName);
        }

        sp = (Spinner) rootview.findViewById(R.id.saspinner1);
        s2 = (Spinner) rootview.findViewById(R.id.saspinner2);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList2);
        adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList3);
        adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList4);
        adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList5);
        adapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList6);
        adapter7 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList7);
        adapter8 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList8);
        adapter9 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList9);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tvStr = ("" +
                        parent.getItemAtPosition(position));
                sendM2 = tvStr;
                switch (hash.get(parent.getItemAtPosition(position))) {
                    case "6420000":
                        sendM = "6420000";
                        s2.setAdapter(adapter2);
                        break;
                    case "6480000":
                        sendM = "6480000";
                        s2.setAdapter(adapter3);
                        break;
                    case "6470000":
                        sendM = "6470000";
                        s2.setAdapter(adapter4);
                        break;
                    case "6460000":
                        sendM = "6460000";
                        s2.setAdapter(adapter5);
                        break;
                    case "6450000":
                        sendM = "6450000";
                        s2.setAdapter(adapter6);
                        break;
                    case "6440000":
                        sendM = "6440000";
                        s2.setAdapter(adapter7);
                        break;
                    case "6430000":
                        sendM = "6430000";
                        s2.setAdapter(adapter8);
                        break;
                    case "6410000":
                        sendM = "6410000";
                        s2.setAdapter(adapter9);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (sendM2) {
                    case "강원도":
                        sendM2 = hash2.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "경상남도":
                        sendM2 = hash3.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "경상북도":
                        sendM2 = hash4.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "전라남도":
                        sendM2 = hash5.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "전라북도":
                        sendM2 = hash6.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "충청남도":
                        sendM2 = hash7.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "충청북도":
                        sendM2 = hash8.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    case "경기도":
                        sendM2 = hash10.get(parent.getItemAtPosition(position));
                        tvStr2 = sendM2;
                        break;
                    default:
                        sendM2 = hash10.get(parent.getItemAtPosition(position));
                        tvStr2 = ("" +
                                parent.getItemAtPosition(position));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnSend = (Button)rootview.findViewById(R.id.btn_send);
        Button btnSend2 = (Button)rootview.findViewById(R.id.btn_send2);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlStr1 = "http://www.returnfarm.com/rtf/m3/n36/business/selectBusinessInfo.do?sido_cd=" + sendM + "&sigun_cd=" + sendM2;
                new FinestWebView.Builder(getContext()).titleDefault("지자체지원사업")
                        .show(urlStr1);
            }
        });
        return rootview;
    }
}