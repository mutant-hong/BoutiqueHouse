package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText search;
    Button searchbtn;
    TextView non;

    RecyclerView productView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> productlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = (EditText)findViewById(R.id.search);
        searchbtn = (Button)findViewById(R.id.searchbtn);
        non = (TextView)findViewById(R.id.non);

        productView = (RecyclerView) findViewById(R.id.productView);
        productView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(layoutManager);

        Intent intent = getIntent();

        productlist = new ArrayList<>();

        String searching = intent.getExtras().getString("search");

        //검색어와 관련된 제품 리스트 보여주기
        if (searching.equals("쇼파")) {
            productlist.add(new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
            productlist.add(new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "new", 1));
            productlist.add(new Product(R.drawable.klippan, "klippan", 169000, 138, 87, 67, "new", 1));
        } else if (searching.equals("침대")) {
            productlist.add(new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "event", 1));
            productlist.add(new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
            productlist.add(new Product(R.drawable.songesand, "songesand", 159000, 207, 133, 136, "new", 1));
        } else if (searching.equals("의자")) {
            productlist.add(new Product(R.drawable.renberget, "renberget", 59900, 59, 65, 108, "event", 1));
            productlist.add(new Product(R.drawable.janinge, "hemnnes", 49900, 50, 46, 76, "hot", 1));
            productlist.add(new Product(R.drawable.kaustby, "songesand", 49900, 44, 48, 103, "event", 1));

        } else if (searching.equals("수납")) {

        } else if (searching.equals("테이블")) {

        } else if (searching.equals("책상")) {

        } else if (searching.equals("주방가구")) {

        } else if (searching.equals("욕실가구")) {

        } else if (searching.equals("조명")) {

        } else if (searching.equals("러그")) {

        } else if (searching.equals("커튼")) {

        } else {
            non.setVisibility(View.VISIBLE);
            productView.setVisibility(View.GONE);
        }

        if(!productlist.isEmpty()) {
            ProductlistAdapter productlistAdapter = new ProductlistAdapter(productlist);
            productView.setAdapter(productlistAdapter);
        }

    }

    @Override
    protected void onStart(){
        super.onStart();

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
}
