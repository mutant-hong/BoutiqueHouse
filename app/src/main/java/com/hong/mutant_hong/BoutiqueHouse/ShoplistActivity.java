package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class ShoplistActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    TextView shopping;

    RecyclerView shoplistView;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<Product> shoppinglist;
    ShoplistAdapter shoplistAdapter;

    LinearLayout listlayout;

    Button tointerior;

    int index = 0;

    //로그인시 장바구니 동기화 한번만
    static boolean userlistFlag = false;

    //장바구니 수정 삭제
    static boolean changeItem = false;
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
        tointerior = (Button)findViewById(R.id.tointerior);

        homebtn.setBackgroundColor(Color.rgb(213,213,213));
        categorybtn.setBackgroundColor(Color.rgb(213,213,213));
        shoplistbtn.setBackgroundColor(Color.rgb(160,186,237));
        loginbtn.setBackgroundColor(Color.rgb(213,213,213));

        shoplistView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        shoplistView.setLayoutManager(layoutManager);

        shoppinglist = new ArrayList<>();

        Intent intent = getIntent();

        shoplistAdapter = new ShoplistAdapter(shoppinglist);

        loginShoppingList();

        try{
            add(intent.getExtras().getString("name"), intent.getExtras().getInt("amount"));
            shoplistView.setAdapter(shoplistAdapter);

        }catch (Exception e){

        }
    }

    protected void onStart() {
        super.onStart();

        Log.d("ShoplistActivity", "onStart");


        tointerior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InteriorActivity.class);
                startActivity(intent);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
    }

    @Override
    protected  void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d("ShoplistActivity", "onNewIntent");
        
        if(shoppinglist.isEmpty()){
            listlayout.setVisibility(View.VISIBLE);
            shoplistView.setVisibility(View.GONE);
        }

        //로그아웃하면 쇼핑리스트 리셋
        if(LoginActivity.logout == true){
            Log.d("로그아웃", "리스트 리셋");
            shoppinglist.clear();
            shoplistView.setAdapter(shoplistAdapter);

            if(shoppinglist.isEmpty()){
                listlayout.setVisibility(View.VISIBLE);
                shoplistView.setVisibility(View.GONE);
            }

            LoginActivity.logout = false;
        }

        loginShoppingList();

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

        }catch (Exception e) {

        }

        if(changeItem == true){
            Log.d("아이템 수정", "수정적용");

            if(LoginActivity.loginstate == true){
                SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
                SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
                Gson gson = new Gson();

                String json = gson.toJson(shoppinglist);
                Log.d("user 쇼핑리스트 동기화", json);
                shopPrefs_Editor.putString(LoginActivity.user, json);
                shopPrefs_Editor.commit();
            }

            changeItem = false;
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

        if(LoginActivity.loginstate == true){
            SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
            SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
            Gson gson = new Gson();

            String json = gson.toJson(shoppinglist);
            Log.d("user 쇼핑리스트 동기화", json);
            shopPrefs_Editor.putString(LoginActivity.user, json);
            shopPrefs_Editor.commit();
        }

        if(!shoppinglist.isEmpty()){
            listlayout.setVisibility(View.GONE);
            shoplistView.setVisibility(View.VISIBLE);
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

        if(LoginActivity.loginstate == true){
            SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
            SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
            Gson gson = new Gson();

            String json = gson.toJson(shoppinglist);
            Log.d("user 쇼핑리스트 동기화", json);
            shopPrefs_Editor.putString(LoginActivity.user, json);
            shopPrefs_Editor.commit();
        }

        if(!shoppinglist.isEmpty()){
            listlayout.setVisibility(View.GONE);
            shoplistView.setVisibility(View.VISIBLE);
        }
    }

    private void loginShoppingList(){

        if(LoginActivity.loginstate == true && userlistFlag == false){

            Log.d("loginShoppingList", "ㅇㅇ");
            SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
            SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();

            String jsonShoppingList = shopPrefs.getString(LoginActivity.user, "0");
            Log.d("user 쇼핑리스트", jsonShoppingList);
            Gson gson = new Gson();

            //회원의 장바구니 비어있지않음
            if(!jsonShoppingList.equals("0")){
                Log.d("회원의 장바구니 비어있지않음", "dd");

                ArrayList<Product> arrayList = gson.fromJson(jsonShoppingList, new TypeToken<ArrayList<Product>>(){}.getType());

                //로그인 전 장바구니와 로그인 상태일 때 저장된 장바구니 합치기
                if(!shoppinglist.isEmpty()) {

                    boolean same = false;

                    for (int i = 0; i < arrayList.size(); i++) {

                        for (int j = 0; j < shoppinglist.size(); j++) {

                            if (arrayList.get(i).name.equals(shoppinglist.get(j).name)) {
                                //같은 아이템이 있을때 장바구니 업데이트

                                int amount = shoppinglist.get(j).amount + arrayList.get(i).amount;
                                set(j, shoppinglist.get(j).name, amount);

                                same = true;
                                break;
                            }

                            else{
                                same = false;
                            }
                        }
                        //같은 아이템 없을때, 장바구니에 추가
                        if(same == false){
                            add(arrayList.get(i).name, arrayList.get(i).amount);
                        }
                    }
                }
                //로그인전 장바구니가 비어있다면
                else{
                    for (int i = 0; i < arrayList.size(); i++) {
                        add(arrayList.get(i).name, arrayList.get(i).amount);
                    }
                }
            }

            String json = gson.toJson(shoppinglist);
            Log.d("user 쇼핑리스트 동기화", json);
            shopPrefs_Editor.putString(LoginActivity.user, json);
            shopPrefs_Editor.commit();

            shoplistView.setAdapter(shoplistAdapter);
            userlistFlag = true;
        }
    }

    public static void changeItem(){
        //changeItem = true;
    }
}
