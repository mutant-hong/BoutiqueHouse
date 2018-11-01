package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    ImageButton imgbtn;
    EditText search;

    static ArrayList<String> list;
    static int cnt=0;
    LinearLayout view;
    ImageView non;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        searchbtn = (Button)findViewById(R.id.searchbtn);
        imgbtn = (ImageButton)findViewById(R.id.imgbtn);
        search = (EditText)findViewById(R.id.search);

        non = (ImageView)findViewById(R.id.non);
        view = (LinearLayout) findViewById(R.id.view);
        list = new ArrayList<>();

    }

    @Override
    protected void onStart(){
        super.onStart();

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

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "imgbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("category", imgbtn.getTag().toString());
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity", "onRestart");

        if(list.size() != 0){
            non.setVisibility(View.GONE);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (; cnt < list.size(); cnt++){
            System.out.print("cnt = " + cnt);
            final TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(list.get(cnt));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setBackgroundResource(R.drawable.bck);
            textView.setId(cnt);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Toast.makeText(MainActivity.this, list.get(textView.getId()).toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                        intent.putExtra("category", list.get(textView.getId()).toString());
                        startActivity(intent);

                    }catch (Exception e){

                    }

                }
            });

            view.addView(textView);


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
