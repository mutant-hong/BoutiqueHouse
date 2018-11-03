package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoplistActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    TextView shopping;

    RecyclerView shoplistView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> shoppinglist;
    ShoplistAdapter shoplistAdapter;

    LinearLayout listlayout;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);

        Log.d("ShoplistActivity", "onCreate");

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        shopping = (TextView) findViewById(R.id.shopping);

        listlayout = (LinearLayout)findViewById(R.id.listlayout);

        shoplistView = (RecyclerView)findViewById(R.id.ShoplistView);
        shoplistView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        shoplistView.setLayoutManager(layoutManager);

        shoppinglist = new ArrayList<>();

        Intent intent = getIntent();

        shoplistAdapter = new ShoplistAdapter(shoppinglist);

        try{
            add(intent.getExtras().getString("name"), intent.getExtras().getInt("amount"));
            shoplistView.setAdapter(shoplistAdapter);

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
    protected  void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d("ShoplistActivity", "onNewIntent");
        boolean same = false;

        try{
            //쇼핑리스트 재방문

            for (int j = 0; j < shoppinglist.size(); j++) {
                if (intent.getExtras().getString("name").toString().equals(shoppinglist.get(j).name)) {
                    same = true;
                    index = j;
                    break;
                }
            }

            //등록한 상품이, 이미 있으면
            if(same == true){
                int addAmount = shoppinglist.get(index).amount + intent.getExtras().getInt("amount");
                set(index, intent.getExtras().getString("name"), addAmount);
            }

            //없으면

            else{
                add(intent.getExtras().getString("name"), intent.getExtras().getInt("amount"));
            }

            shoplistView.setAdapter(shoplistAdapter);

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

    private void add(String name, int amount){

        listlayout.setVisibility(View.GONE);
        shoplistView.setVisibility(View.VISIBLE);

        if(name.equals("landskrona")){
            shoppinglist.add(new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", amount));

        }
        else if(name.equals("kivik")){
            shoppinglist.add(new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "normal", amount));

        }
        else if(name.equals("tarva")){
            shoppinglist.add(new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "normal", amount));

        }
        else if(name.equals("hemnnes")){
            shoppinglist.add(new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", amount));

        }
    }

    private void set(int index, String name, int amount){

        if(name.equals("landskrona")){
            shoppinglist.set(index, new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", amount));

        }
        else if(name.equals("kivik")){
            shoppinglist.set(index, new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "normal", amount));

        }
        else if(name.equals("tarva")){
            shoppinglist.set(index, new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "normal", amount));

        }
        else if(name.equals("hemnnes")){
            shoppinglist.set(index, new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", amount));

        }
    }

}
