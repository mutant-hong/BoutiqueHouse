package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button eventbtn, newbtn, hotbtn;
    HorizontalScrollView eventLayout, newLayout, hotLayout;
    ImageButton tarvabtn, landskronabtn, kivikbtn, hemnnesbtn;

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    EditText search;

    static ArrayList<String> list;
    int cnt=0;
    LinearLayout view;
    ImageView non;

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
        list = new ArrayList<>();

        eventbtn.setFocusableInTouchMode(true);
        eventbtn.requestFocus();

        mContext = getApplicationContext();

        getHashKey(mContext);

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

        eventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.VISIBLE);
                newLayout.setVisibility(View.GONE);
                hotLayout.setVisibility(View.GONE);
            }
        });

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.GONE);
                newLayout.setVisibility(View.VISIBLE);
                hotLayout.setVisibility(View.GONE);
            }
        });

        hotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventLayout.setVisibility(View.GONE);
                newLayout.setVisibility(View.GONE);
                hotLayout.setVisibility(View.VISIBLE);
            }
        });

        tarvabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "tarva");
                startActivity(intent);
            }
        });

        landskronabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "landskrona");
                startActivity(intent);
            }
        });

        kivikbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "kivik");
                startActivity(intent);
            }
        });

        hemnnesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("name", "hemnnes");
                startActivity(intent);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "homebtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "categorybtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "shoplistbtn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "loginbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searching = search.getText().toString();

                Toast.makeText(MainActivity.this, searching, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity", "onRestart");

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
                        Toast.makeText(MainActivity.this, list.get(imageButton.getId()).toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                        intent.putExtra("name", imageButton.getTag().toString());
                        startActivity(intent);

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

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", "onPause");
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
}
