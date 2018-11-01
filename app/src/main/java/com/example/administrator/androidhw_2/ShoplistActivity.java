package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoplistActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    TextView shopping;

    static  ArrayList<String> shoppinglist;

    LinearLayout listlayout;
    static String list = "";
    static int i=0;

    int index = 0;
    int amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        shopping = (TextView) findViewById(R.id.shopping);

        listlayout = (LinearLayout)findViewById(R.id.listlayout);
        shoppinglist = new ArrayList<>();

        Log.d("ShoplistActivity", "onCreate");

        Intent intent = getIntent();
        try{
            Log.d("ShoplistActivity", "try catch");
            Log.d("ShoplistActivity", intent.getExtras().getString("name").toString());

            if (shoppinglist.size() == 0){
                shoppinglist.add(intent.getExtras().getString("name"));
            }
            for (; i < shoppinglist.size(); i++) {
                System.out.println(shoppinglist);

                final TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText(shoppinglist.get(i));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setBackgroundResource(R.drawable.bck);
                textView.setId(i);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                        intent2.putExtra("category", shoppinglist.get(textView.getId()));
                        startActivity(intent2);
                    }
                });

                listlayout.addView(textView);
            }

        }catch (Exception e){

        }
    }

    protected void onStart() {
        super.onStart();

        Log.d("ShoplistActivity", "onStart");

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoplistActivity.this, "homebtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoplistActivity.this, "categorybtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoplistActivity.this, "shoplistbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //쇼핑리스트에서 쇼핑리스트로 재사용
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoplistActivity.this, "loginbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    protected  void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        try{
            Log.d("ShoplistActivity", "try catch");
            Log.d("ShoplistActivity", intent.getExtras().getString("name").toString());

           // if (shoppinglist.size() == 0){
           //     shoppinglist.add(intent.getExtras().getString("name").toString());
           // }

            boolean same = false;

           // if (shoppinglist.size() != 0){
           //     shopping.setVisibility(View.GONE);

                for(int j = 0; j < shoppinglist.size(); j++){
                    if(intent.getExtras().getString("name").toString().equals(shoppinglist.get(j))){
                        same = true;
                        index = j;
                        break;
                    }
                }
           // }

            if(same == true){
                TextView textView = (TextView)findViewById(index);
                String str = textView.getText().toString();
                textView.setText(str + "\n +1");
            }

            else{
                shopping.setVisibility(View.GONE);
                shoppinglist.add(intent.getExtras().getString("name"));

                for (; i < shoppinglist.size(); i++) {
                    System.out.println(shoppinglist);

                    final TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setText(shoppinglist.get(i));
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setBackgroundResource(R.drawable.bck);
                    textView.setId(i);

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2 = new Intent(getApplicationContext(), ProductActivity.class);
                            intent2.putExtra("category", shoppinglist.get(textView.getId()));
                            startActivity(intent2);
                        }
                    });

                    listlayout.addView(textView);
                }
            }

        }catch (Exception e){

        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("ShoplistActivity", "onRestart");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("ShoplistActivity", "onResume");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("ShoplistActivity", "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("ShoplistActivity", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ShoplistActivity", "onDestroy");
    }
}
