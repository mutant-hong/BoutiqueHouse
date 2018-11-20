package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button eventbtn, newbtn, hotbtn;
    TextView prAtrribute;
    HorizontalScrollView eventLayout, newLayout, hotLayout;
    ImageButton tarvabtn, landskronabtn, kivikbtn, hemnnesbtn;

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    EditText search;

    static ArrayList<String> list;
    int cnt=0;
    LinearLayout view;
    ImageView non;

    Button up, down;

    RecyclerView searchitem;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<Search> searchlist;
    ArrayList<String> showsearchlist;

    TextView num, prName, top10;
    Thread th;
    Handler han;
    int count = 0;
    boolean threadflag = true;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("search");

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        eventbtn = (Button)findViewById(R.id.eventbtn);
        newbtn = (Button)findViewById(R.id.newbtn);
        hotbtn = (Button)findViewById(R.id.hotbtn);

        eventLayout = (HorizontalScrollView) findViewById(R.id.eventlayout);
        newLayout = (HorizontalScrollView) findViewById(R.id.newlayout);
        hotLayout = (HorizontalScrollView) findViewById(R.id.hotlayout);

        prAtrribute = (TextView)findViewById(R.id.prAtrribute);

        tarvabtn = (ImageButton)findViewById(R.id.tarvabtn);
        landskronabtn = (ImageButton)findViewById(R.id.landskronabtn);
        kivikbtn = (ImageButton)findViewById(R.id.kivikbtn);
        hemnnesbtn = (ImageButton)findViewById(R.id.hemnnesbtn);

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        searchbtn = (Button)findViewById(R.id.searchbtn);

        search = (EditText)findViewById(R.id.search);

        non = (ImageView)findViewById(R.id.non);
        view = (LinearLayout) findViewById(R.id.view);

        num = (TextView)findViewById(R.id.num);
        prName = (TextView)findViewById(R.id.prName);
        top10 = (TextView)findViewById(R.id.top10);

        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);

        list = new ArrayList<>();

        searchitem = (RecyclerView)findViewById(R.id.searchitem);
        searchitem.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        searchitem.setLayoutManager(layoutManager);

        searchlist = new ArrayList<>();

        showsearchlist = new ArrayList<>();

        eventbtn.setFocusableInTouchMode(true);
        eventbtn.requestFocus();

        homebtn.setBackgroundColor(Color.rgb(160,186,237));
        categorybtn.setBackgroundColor(Color.rgb(213,213,213));
        shoplistbtn.setBackgroundColor(Color.rgb(213,213,213));
        loginbtn.setBackgroundColor(Color.rgb(213,213,213));

        eventbtn.setBackgroundColor(Color.rgb(160,186,237));
        newbtn.setBackgroundColor(Color.rgb(213,213,213));
        hotbtn.setBackgroundColor(Color.rgb(213,213,213));

        mContext = getApplicationContext();

        getHashKey(mContext);

        try {
            SharedPreferences loginState = getSharedPreferences("loginState", MODE_PRIVATE);
            SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
            String login = loginState.getString("login", "0");

            if(!userPrefs.getString(login,"0").equals("0")){
                Log.d("자동로그인","성공");
                LoginActivity.user = userPrefs.getString(login,"0");
                LoginActivity.loginstate = true;
            }
        }catch (Exception e){

        }
    }
    @Nullable

    public static String getHashKey(Context context) {

        final String TAG = "KeyHash";

        String keyHash = null;

        try {

            PackageInfo info =

                    context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);



            for (Signature signature : info.signatures) {

                MessageDigest md;

                md = MessageDigest.getInstance("SHA");

                md.update(signature.toByteArray());

                keyHash = new String(Base64.encode(md.digest(), 0));

                Log.d(TAG, keyHash);

            }

        } catch (Exception e) {

            Log.e("name not found", e.toString());

        }



        if (keyHash != null) {

            return keyHash;

        } else {

            return null;

        }

    }

    @Override
    protected void onStart(){
        super.onStart();

        //파이어베이스 데이터 불러오기
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!searchlist.isEmpty())
                    searchlist.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Log.d("data", snapshot.getKey() + " " + snapshot.getValue().toString());

                    searchlist.add(new Search(snapshot.getKey(), Integer.parseInt(snapshot.getValue().toString())));
                }

                Collections.sort(searchlist);

                Log.d("size", Integer.toString(searchlist.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //실검 1~10까지 리스트형식으로

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.setVisibility(View.GONE);
                prName.setVisibility(View.GONE);
                top10.setVisibility(View.VISIBLE);

                if(showsearchlist.isEmpty()) {
                    for (int i = 0; i < searchlist.size() && i < 10; i++) {
                        showsearchlist.add(searchlist.get(i).category);
                    }
                }

                SearchlistAdapter searchlistAdapter = new SearchlistAdapter(showsearchlist);
                searchitem.setAdapter(searchlistAdapter);

                searchitem.setVisibility(View.VISIBLE);
                up.setVisibility(View.VISIBLE);
                down.setVisibility(View.GONE);

                threadflag = false;
            }
        });

        //리스트 숨기기

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num.setVisibility(View.VISIBLE);
                prName.setVisibility(View.VISIBLE);
                top10.setVisibility(View.GONE);

                showsearchlist.clear();

                searchitem.setVisibility(View.GONE);
                up.setVisibility(View.GONE);
                down.setVisibility(View.VISIBLE);

                threadflag = true;

                searchTop10Thread();
            }
        });

        eventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.VISIBLE);
                newLayout.setVisibility(View.GONE);
                hotLayout.setVisibility(View.GONE);

                prAtrribute.setText("이벤트상품");

                eventbtn.setBackgroundColor(Color.rgb(160,186,237));
                newbtn.setBackgroundColor(Color.rgb(213,213,213));
                hotbtn.setBackgroundColor(Color.rgb(213,213,213));
            }
        });

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.GONE);
                newLayout.setVisibility(View.VISIBLE);
                hotLayout.setVisibility(View.GONE);

                prAtrribute.setText("신상품");

                eventbtn.setBackgroundColor(Color.rgb(213,213,213));
                newbtn.setBackgroundColor(Color.rgb(160,186,237));
                hotbtn.setBackgroundColor(Color.rgb(213,213,213));
            }
        });

        hotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.GONE);
                newLayout.setVisibility(View.GONE);
                hotLayout.setVisibility(View.VISIBLE);

                prAtrribute.setText("인기상품");

                eventbtn.setBackgroundColor(Color.rgb(213,213,213));
                newbtn.setBackgroundColor(Color.rgb(213,213,213));
                hotbtn.setBackgroundColor(Color.rgb(160,186,237));
            }
        });

        tarvabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "tarva");
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        landskronabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "landskrona");
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        kivikbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "kivik");
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        hemnnesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "hemnnes");
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searching = search.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("search", searching);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity", "onRestart");

        threadflag = true;

        final int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, getResources().getDisplayMetrics());
        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

        if(list.size() != 0){
            non.setVisibility(View.GONE);
        }

        for (; cnt < list.size(); cnt++){
            Log.d("for문", "for");
            Log.d("name", list.get(cnt));

            final ImageButton imageButton = new ImageButton(this);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(width, height));

            if(list.get(cnt).equals("hemnnes")){
                imageButton.setImageResource(R.drawable.hemnnes);
            }
            else if(list.get(cnt).equals("kivik")){
                imageButton.setImageResource(R.drawable.kivik);
            }
            else if(list.get(cnt).equals("landskrona")){
                imageButton.setImageResource(R.drawable.landskrona);
            }
            else if(list.get(cnt).equals("tarva")){
                Log.d("dd","dd");
                imageButton.setImageResource(R.drawable.tarva);
            }
            imageButton.setBackgroundColor(Color.WHITE);
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            imageButton.setTag(list.get(cnt));
            imageButton.setId(cnt);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                        intent.putExtra("name", imageButton.getTag().toString());
                        startActivity(intent);
                        overridePendingTransition(0,0);

                    }catch (Exception e){

                    }
                }
            });

            view.addView(imageButton);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", "onResume");

        searchTop10Thread();

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", "onPause");
        //overridePendingTransition(0,0);

        threadflag = false;
        Log.d("스레드", "interrupted");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");

    }

    public void searchTop10Thread(){
        //핸들러 순위, 제품명 set
        han = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(searchlist.size() < msg.arg1+1){
                    num.setText(Integer.toString(msg.arg1+1));
                    prName.setText("null");
                }
                else {
                    num.setText(Integer.toString(msg.arg1 + 1));
                    prName.setText(searchlist.get(msg.arg1).category);
                }
            }
        };

        //스레드 1~9까지 1초마다 ++
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadflag) {
                    try {
                        Log.d("스레드", "running");
                        Message msg = han.obtainMessage();

                        msg.arg1 = count;
                        han.sendMessage(msg);
                        count++;

                        Thread.sleep(2000);

                        //count 초기화 -> 처음부터 계속 반복
                        if(count == 10)
                            count = 0;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        Log.d("스레드", "stop");
                    }
                }
            }
        });
        th.start();

    }
}
