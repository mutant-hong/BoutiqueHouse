package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Hashtable;

public class SearchActivity extends AppCompatActivity {

    EditText search;
    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;
    TextView non;

    RecyclerView productView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> productlist;
    String searching;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("search");
    static Hashtable<String, String> searchcountlist;
    boolean searchflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = (EditText)findViewById(R.id.search);
        searchbtn = (Button)findViewById(R.id.searchbtn);
        non = (TextView)findViewById(R.id.non);

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        productView = (RecyclerView) findViewById(R.id.productView);
        productView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(layoutManager);

        homebtn.setBackgroundColor(Color.rgb(160,186,237));
        categorybtn.setBackgroundColor(Color.rgb(213,213,213));
        shoplistbtn.setBackgroundColor(Color.rgb(213,213,213));
        loginbtn.setBackgroundColor(Color.rgb(213,213,213));

        Intent intent = getIntent();

        productlist = new ArrayList<>();
        searchcountlist = new Hashtable<>();

        searching = intent.getExtras().getString("search");

        //검색어와 관련된 제품 리스트 보여주기
        if (searching.equals("쇼파")) {
            productlist.add(new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
            productlist.add(new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "new", 1));
            productlist.add(new Product(R.drawable.klippan, "klippan", 169000, 138, 87, 67, "normal", 1));
            productlist.add(new Product(R.drawable.brathult, "brathult", 299000, 228, 138, 83, "normal", 1));
            productlist.add(new Product(R.drawable.ektorp, "ektorp", 1699000, 138, 87, 67, "normal", 1));
            productlist.add(new Product(R.drawable.holarna, "holarna", 1699000, 138, 87, 67, "normal", 1));
            productlist.add(new Product(R.drawable.lidhult, "lidhult", 1899000, 155, 93, 83, "normal", 1));
            productlist.add(new Product(R.drawable.stocksund, "stocksund", 2099000, 155, 93, 83, "normal", 1));
        }
        else if (searching.equals("침대")) {
            productlist.add(new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "event", 1));
            productlist.add(new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
            productlist.add(new Product(R.drawable.songesand, "songesand", 159000, 133, 207, 136, "normal", 1));
            productlist.add(new Product(R.drawable.glimsbu, "glimsbu", 189000, 154, 211, 188, "normal", 1));
            //어린이침대
            productlist.add(new Product(R.drawable.mydal, "mydal", 389000, 154, 211, 335, "normal", 1));
            productlist.add(new Product(R.drawable.slakt, "slakt", 179000, 140, 188, 136, "normal", 1));
            productlist.add(new Product(R.drawable.svarta, "svarta", 139000, 154, 211, 188, "normal", 1));
        }
        else if (searching.equals("의자")) {
            productlist.add(new Product(R.drawable.renberget, "renberget", 59900, 59, 65, 108, "normal", 1));
            productlist.add(new Product(R.drawable.janinge, "janinge", 49900, 50, 46, 76, "normal", 1));
            productlist.add(new Product(R.drawable.kaustby, "kaustby", 49900, 44, 48, 103, "normal", 1));
            productlist.add(new Product(R.drawable.ingolf, "ingolf", 79900, 59, 65, 108, "normal", 1));
            productlist.add(new Product(R.drawable.norraryd, "norraryd", 69900, 50, 46, 76, "normal", 1));
            productlist.add(new Product(R.drawable.odger, "odger", 69900, 44, 48, 103, "normal", 1));
        }
        else if (searching.equals("수납")) {
            //거실수납
            productlist.add(new Product(R.drawable.billy, "billy", 149000, 78, 41, 95, "normal", 1));
            productlist.add(new Product(R.drawable.fredde, "fredde", 289000, 78, 41, 195, "normal", 1));
            productlist.add(new Product(R.drawable.lixhult, "lixhult", 198000, 58, 41, 88, "normal", 1));
            productlist.add(new Product(R.drawable.malsjo, "malsjo", 339000, 88, 41, 105, "normal", 1));
            productlist.add(new Product(R.drawable.svalnas, "svalnas", 198000, 58, 41, 885, "normal", 1));
            productlist.add(new Product(R.drawable.vittsjo, "vittsjo", 249000, 158, 41, 105, "normal", 1));
            //침실수납
            productlist.add(new Product(R.drawable.askvoll, "askvoll", 269000, 98, 51, 225, "normal", 1));
            productlist.add(new Product(R.drawable.bekant, "bekant", 379000, 105, 51, 225, "normal", 1));
            productlist.add(new Product(R.drawable.brimnes, "brimnes", 229000, 105, 51, 125, "normal", 1));
            productlist.add(new Product(R.drawable.malm, "malm", 345000, 120, 51, 125, "normal", 1));
        }
        else if (searching.equals("테이블")) {
            productlist.add(new Product(R.drawable.ekedalen, "ekedalen", 169000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.fanom, "fanom", 99000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.ryggestad, "ryggestad", 125000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.torsby, "torsby", 88000, 125, 125, 140, "normal", 1));
        }
        else if (searching.equals("책상")) {
            productlist.add(new Product(R.drawable.micke, "micke", 125000, 125, 85, 100, "normal", 1));
            productlist.add(new Product(R.drawable.pahl, "pahl", 98000, 125, 85, 100, "normal", 1));
            //어린이책상
            productlist.add(new Product(R.drawable.beant, "beant", 105000, 115, 75, 85, "normal", 1));
            productlist.add(new Product(R.drawable.flisat, "flisat", 128000, 115, 75, 85, "normal", 1));
        }
        else if (searching.equals("주방가구")) {
            //테이블
            productlist.add(new Product(R.drawable.ekedalen, "ekedalen", 169000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.fanom, "fanom", 99000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.ryggestad, "ryggestad", 125000, 125, 125, 140, "normal", 1));
            productlist.add(new Product(R.drawable.torsby, "torsby", 88000, 125, 125, 140, "normal", 1));
            //주방수납
            productlist.add(new Product(R.drawable.liatorp, "liatorp", 569000, 125, 85, 220, "normal", 1));
            productlist.add(new Product(R.drawable.malso, "malso", 399000, 125, 90, 120, "normal", 1));
            productlist.add(new Product(R.drawable.regossor, "regossor", 425000, 125, 85, 190, "normal", 1));
        }
        else if (searching.equals("욕실가구")) {
            //욕실거울수납
            productlist.add(new Product(R.drawable.godmorgon, "godmorgon", 169000, 65, 20, 80, "normal", 1));
            productlist.add(new Product(R.drawable.isberget, "isberget", 199000, 65, 20, 80, "normal", 1));
            productlist.add(new Product(R.drawable.lillangen, "lillangen", 125000, 65, 20, 80, "normal", 1));
            //욕실수납
            productlist.add(new Product(R.drawable.brusali, "brusali", 89000, 85, 60, 205, "normal", 1));
            productlist.add(new Product(R.drawable.dynan, "dynan", 99000, 85, 80, 205, "normal", 1));
        }
        else if (searching.equals("조명")) {
            productlist.add(new Product(R.drawable.foto, "foto", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.ldasen, "ldasen", 19000, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.ranarp, "ranarp", 69900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.stakra, "stakra", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.strala, "strala", 19000, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.vindkare, "vindkare", 69900, 0, 0, 0, "normal", 1));
        }
        else if (searching.equals("러그")) {
            productlist.add(new Product(R.drawable.adum, "adum", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.hampen, "hampen", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.hodde, "hodde", 79900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.kristrup, "kristrup", 79900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.sivested, "sivested", 69900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.stockholm, "stockholm", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.trampa, "trampa", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.vinter, "vinter", 49900, 0, 0, 0, "normal", 1));

        }
        else if (searching.equals("커튼")) {
            productlist.add(new Product(R.drawable.glansnava, "glansnava", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.hilja, "hilja", 49900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.lejongap, "lejongap", 49900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.lisavritt, "lisavritt", 69900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.merete, "merete", 69900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.vilvorg, "stockholm", 59900, 0, 0, 0, "normal", 1));
            productlist.add(new Product(R.drawable.vivan, "vivan", 49900, 0, 0, 0, "normal", 1));
        }
        else {
            non.setVisibility(View.VISIBLE);
            productView.setVisibility(View.GONE);
            searchflag = false;
        }

        if(!productlist.isEmpty()) {
            ProductlistAdapter productlistAdapter = new ProductlistAdapter(productlist);
            productView.setAdapter(productlistAdapter);
        }

    }

    @Override
    protected void onStart(){
        super.onStart();

        //파이어베이스 데이터 불러오기
        conditionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String count = "0";
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    if(dataSnapshot.getKey() != null){
                        Log.d("data", snapshot.getKey() + " " + snapshot.getValue().toString());

                        //이미 검색
                        if(snapshot.getKey().equals(searching)){
                            count = snapshot.getValue().toString();
                            break;
                        }
                        //처음 검색
                        else{
                            count = "0";
                        }
                    }
                }

                if(searching.equals("쇼파") || searching.equals("침대") || searching.equals("의자") || searching.equals("수납") ||
                        searching .equals("테이블") || searching.equals("책상") || searching.equals("주방가구") || searching.equals("욕실가구") ||
                        searching.equals("조명") || searching.equals("러그") || searching.equals("커튼")) {
                    conditionRef.child(searching).setValue(Integer.parseInt(count) + 1);
                    searchcountlist.put(searching, Integer.toString(Integer.parseInt(count) + 1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searching = search.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("search", searching);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Log.d("SearchActivity", "onBackPressed");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }
}
