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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Hashtable;

public class ShoplistActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn, buybtn;
    TextView shopping;

    RecyclerView shoplistView;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<Product> shoppinglist;
    ShoplistAdapter shoplistAdapter;

    LinearLayout listlayout;

    TextView totalText, totalPrice;

    Button tointerior;

    int index = 0;

    //장바구니 총가격
    int totalprice;

    //로그인시 장바구니 동기화 한번만
    static boolean userlistFlag = false;

    //장바구니 수정 삭제
    static boolean changeItem = false;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("buy");

    Hashtable<String, Product> productInfolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);

        Log.d("ShoplistActivity", "onCreate");

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        buybtn = (Button)findViewById(R.id.buybtn);

        shopping = (TextView) findViewById(R.id.shopping);

        listlayout = (LinearLayout)findViewById(R.id.listlayout);

        shoplistView = (RecyclerView)findViewById(R.id.ShoplistView);
        tointerior = (Button)findViewById(R.id.tointerior);

        totalText = (TextView)findViewById(R.id.totalText);
        totalPrice = (TextView)findViewById(R.id.totalPrice);

        homebtn.setBackgroundColor(Color.rgb(213,213,213));
        categorybtn.setBackgroundColor(Color.rgb(213,213,213));
        shoplistbtn.setBackgroundColor(Color.rgb(160,186,237));
        loginbtn.setBackgroundColor(Color.rgb(213,213,213));

        productInfolist = new Hashtable<>();
        productInfo();

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

        //구매
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //로그인 여부 확인
                if(LoginActivity.loginstate){

                    //장바구니가 비어있지않을때
                    if(!shoppinglist.isEmpty()) {
                        Toast.makeText(ShoplistActivity.this, "구매가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < shoppinglist.size(); i++) {
                            conditionRef.child(LoginActivity.user).child(shoppinglist.get(i).name).setValue("구매");
                        }
                        //장바구니 초기화
                        shoppinglist.clear();
                        //shared 동기화

                        SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
                        SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
                        Gson gson = new Gson();

                        String json = gson.toJson(shoppinglist);
                        Log.d("user 쇼핑리스트 동기화", json);
                        shopPrefs_Editor.putString(LoginActivity.user, json);
                        shopPrefs_Editor.commit();

                        if(shoppinglist.isEmpty()){
                            listlayout.setVisibility(View.VISIBLE);
                            shoplistView.setVisibility(View.GONE);
                            totalText.setVisibility(View.GONE);
                            totalPrice.setVisibility(View.GONE);
                        }
                    }

                    else{
                        Toast.makeText(ShoplistActivity.this, "장바구니가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                //로그인 x
                else{
                    Toast.makeText(ShoplistActivity.this, "구매를 위해 로그인 해주세요.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
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

        //총금액 계산
        totalprice = 0;

        if(shoppinglist.isEmpty()){
            totalText.setVisibility(View.GONE);
            totalPrice.setVisibility(View.GONE);
        }
        else{
            for(int i = 0; i < shoppinglist.size(); i++){
                totalprice += shoppinglist.get(i).price * shoppinglist.get(i).amount;
            }

            totalText.setVisibility(View.VISIBLE);
            totalPrice.setVisibility(View.VISIBLE);

            totalPrice.setText("￦ " + Integer.toString(totalprice));
        }

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
        Log.d("pr", productInfolist.get(name).name + ", " + productInfolist.get(name).drawableId);
        shoppinglist.add(new Product(productInfolist.get(name).drawableId,
                productInfolist.get(name).name,
                productInfolist.get(name).price,
                productInfolist.get(name).w,
                productInfolist.get(name).h,
                productInfolist.get(name).d,
                productInfolist.get(name).attribute,
                amount)
        );
        /*
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
        */

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

        shoppinglist.set(index, new Product(Product.productInfolist.get(name).drawableId,
                Product.productInfolist.get(name).name,
                Product.productInfolist.get(name).price,
                Product.productInfolist.get(name).w,
                Product.productInfolist.get(name).h,
                Product.productInfolist.get(name).d,
                Product.productInfolist.get(name).attribute,
                amount)
        );

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
                Log.d("회원의 장바구니 비어있지않음", jsonShoppingList);

                ArrayList<Product> arrayList = gson.fromJson(jsonShoppingList, new TypeToken<ArrayList<Product>>(){}.getType());

                //로그인 전 장바구니와 로그인 상태일 때 저장된 장바구니 합치기
                if(!shoppinglist.isEmpty()) {
                    Log.d("empty", "ㅇ");
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
                    Log.d("empty", "x");
                    for (int i = 0; i < arrayList.size(); i++) {
                        Log.d("array", arrayList.size() + ", " + arrayList.get(i).name);
                        add(arrayList.get(i).name, arrayList.get(i).amount);
                    }
                }

                String json = gson.toJson(shoppinglist);
                Log.d("user 쇼핑리스트 동기화", json);
                shopPrefs_Editor.putString(LoginActivity.user, json);
                shopPrefs_Editor.commit();

                shoplistView.setAdapter(shoplistAdapter);
                userlistFlag = true;
            }
            //회원 장바구니 비어있음
            else{
                //로그인전 장바구니 비어있지않으면
                if(!shoppinglist.isEmpty()){
                    String json = gson.toJson(shoppinglist);
                    Log.d("user 쇼핑리스트 동기화", json);
                    shopPrefs_Editor.putString(LoginActivity.user, json);
                    shopPrefs_Editor.commit();

                    userlistFlag = true;
                }
            }
        }
    }

    public void productInfo(){

        //쇼파
        productInfolist.put("landskrona", new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
        productInfolist.put("kivik", new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "new", 1));
        productInfolist.put("klippan", new Product(R.drawable.klippan, "klippan", 169000, 138, 87, 67, "normal", 1));
        productInfolist.put("brathult", new Product(R.drawable.brathult, "brathult", 299000, 228, 138, 83, "normal", 1));
        productInfolist.put("ektorp", new Product(R.drawable.ektorp, "ektorp", 1699000, 138, 87, 67, "normal", 1));
        productInfolist.put("holarna", new Product(R.drawable.holarna, "holarna", 1699000, 138, 87, 67, "normal", 1));
        productInfolist.put("lidhult", new Product(R.drawable.lidhult, "lidhult", 1899000, 155, 93, 83, "normal", 1));
        productInfolist.put("stocksund", new Product(R.drawable.stocksund, "stocksund", 2099000, 155, 93, 83, "normal", 1));

        //침대
        productInfolist.put("tarva",new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "event", 1));
        productInfolist.put("hemnnes",new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
        productInfolist.put("songesand",new Product(R.drawable.songesand, "songesand", 159000, 133, 207, 136, "normal", 1));
        productInfolist.put("glimsbu",new Product(R.drawable.glimsbu, "glimsbu", 189000, 154, 211, 188, "normal", 1));

        //어린이침대
        productInfolist.put("mydal",new Product(R.drawable.mydal, "mydal", 389000, 154, 211, 335, "normal", 1));
        productInfolist.put("slakt",new Product(R.drawable.slakt, "slakt", 179000, 140, 188, 136, "normal", 1));
        productInfolist.put("svarta",new Product(R.drawable.svarta, "svarta", 139000, 154, 211, 188, "normal", 1));

        //의자
        productInfolist.put("renberget",new Product(R.drawable.renberget, "renberget", 59900, 59, 65, 108, "normal", 1));
        productInfolist.put("janinge",new Product(R.drawable.janinge, "janinge", 49900, 50, 46, 76, "normal", 1));
        productInfolist.put("kaustby",new Product(R.drawable.kaustby, "kaustby", 49900, 44, 48, 103, "normal", 1));
        productInfolist.put("ingolf",new Product(R.drawable.ingolf, "ingolf", 79900, 59, 65, 108, "normal", 1));
        productInfolist.put("norraryd",new Product(R.drawable.norraryd, "norraryd", 69900, 50, 46, 76, "normal", 1));
        productInfolist.put("odger",new Product(R.drawable.odger, "odger", 69900, 44, 48, 103, "normal", 1));

        //거실수납
        productInfolist.put("billy",new Product(R.drawable.billy, "billy", 149000, 78, 41, 95, "normal", 1));
        productInfolist.put("fredde",new Product(R.drawable.fredde, "fredde", 289000, 78, 41, 195, "normal", 1));
        productInfolist.put("lixhult",new Product(R.drawable.lixhult, "lixhult", 198000, 58, 41, 88, "normal", 1));
        productInfolist.put("malsjo",new Product(R.drawable.malsjo, "malsjo", 339000, 88, 41, 105, "normal", 1));
        productInfolist.put("svalnas",new Product(R.drawable.svalnas, "svalnas", 198000, 58, 41, 885, "normal", 1));
        productInfolist.put("vittsjo",new Product(R.drawable.vittsjo, "vittsjo", 249000, 158, 41, 105, "normal", 1));

        //침실수납
        productInfolist.put("askvoll",new Product(R.drawable.askvoll, "askvoll", 269000, 98, 51, 225, "normal", 1));
        productInfolist.put("bekant",new Product(R.drawable.bekant, "bekant", 379000, 105, 51, 225, "normal", 1));
        productInfolist.put("brimnes",new Product(R.drawable.brimnes, "brimnes", 229000, 105, 51, 125, "normal", 1));
        productInfolist.put("malm",new Product(R.drawable.malm, "malm", 345000, 120, 51, 125, "normal", 1));

        //테이블
        productInfolist.put("ekedalen",new Product(R.drawable.ekedalen, "ekedalen", 169000, 125, 125, 140, "normal", 1));
        productInfolist.put("fanom",new Product(R.drawable.fanom, "fanom", 99000, 125, 125, 140, "normal", 1));
        productInfolist.put("ryggestad",new Product(R.drawable.ryggestad, "ryggestad", 125000, 125, 125, 140, "normal", 1));
        productInfolist.put("torsby",new Product(R.drawable.torsby, "torsby", 88000, 125, 125, 140, "normal", 1));

        //책상
        productInfolist.put("micke",new Product(R.drawable.micke, "micke", 125000, 125, 85, 100, "normal", 1));
        productInfolist.put("pahl",new Product(R.drawable.pahl, "pahl", 98000, 125, 85, 100, "normal", 1));

        //어린이책상
        productInfolist.put("beant",new Product(R.drawable.beant, "beant", 105000, 115, 75, 85, "normal", 1));
        productInfolist.put("flisat",new Product(R.drawable.flisat, "flisat", 128000, 115, 75, 85, "normal", 1));

        //주방수납
        productInfolist.put("liatorp",new Product(R.drawable.liatorp, "liatorp", 569000, 125, 85, 220, "normal", 1));
        productInfolist.put("malso",new Product(R.drawable.malso, "malso", 399000, 125, 90, 120, "normal", 1));
        productInfolist.put("regossor",new Product(R.drawable.regossor, "regossor", 425000, 125, 85, 190, "normal", 1));

        //욕실거울수납
        productInfolist.put("godmorgon",new Product(R.drawable.godmorgon, "godmorgon", 169000, 65, 20, 80, "normal", 1));
        productInfolist.put("isberget",new Product(R.drawable.isberget, "isberget", 199000, 65, 20, 80, "normal", 1));
        productInfolist.put("lillangen",new Product(R.drawable.lillangen, "lillangen", 125000, 65, 20, 80, "normal", 1));

        //욕실수납
        productInfolist.put("brusali",new Product(R.drawable.brusali, "brusali", 89000, 85, 60, 205, "normal", 1));
        productInfolist.put("dynan",new Product(R.drawable.dynan, "dynan", 99000, 85, 80, 205, "normal", 1));

        //조명
        productInfolist.put("foto",new Product(R.drawable.foto, "foto", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("ldasen",new Product(R.drawable.ldasen, "ldasen", 19000, 0, 0, 0, "normal", 1));
        productInfolist.put("ranarp",new Product(R.drawable.ranarp, "ranarp", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("stakra",new Product(R.drawable.stakra, "stakra", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("strala",new Product(R.drawable.strala, "strala", 19000, 0, 0, 0, "normal", 1));
        productInfolist.put("vindkare",new Product(R.drawable.vindkare, "vindkare", 69900, 0, 0, 0, "normal", 1));

        //러그
        productInfolist.put("adum",new Product(R.drawable.adum, "adum", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hampen",new Product(R.drawable.hampen, "hampen", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hodde",new Product(R.drawable.hodde, "hodde", 79900, 0, 0, 0, "normal", 1));
        productInfolist.put("kristrup",new Product(R.drawable.kristrup, "kristrup", 79900, 0, 0, 0, "normal", 1));
        productInfolist.put("sivested",new Product(R.drawable.sivested, "sivested", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("stockholm",new Product(R.drawable.stockholm, "stockholm", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("trampa",new Product(R.drawable.trampa, "trampa", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("vinter",new Product(R.drawable.vinter, "vinter", 49900, 0, 0, 0, "normal", 1));

        //커튼
        productInfolist.put("glansnava",new Product(R.drawable.glansnava, "glansnava", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hilja",new Product(R.drawable.hilja, "hilja", 49900, 0, 0, 0, "normal", 1));
        productInfolist.put("lejongap",new Product(R.drawable.lejongap, "lejongap", 49900, 0, 0, 0, "normal", 1));
        productInfolist.put("lisavritt",new Product(R.drawable.lisavritt, "lisavritt", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("merete",new Product(R.drawable.merete, "merete", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("vilvorg",new Product(R.drawable.vilvorg, "stockholm", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("vivan",new Product(R.drawable.vivan, "vivan", 49900, 0, 0, 0, "normal", 1));

    }
}
