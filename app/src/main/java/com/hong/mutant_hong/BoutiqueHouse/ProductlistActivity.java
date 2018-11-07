package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductlistActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;

    RecyclerView productView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> productlist;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        homebtn = (Button) findViewById(R.id.homebtn);
        categorybtn = (Button) findViewById(R.id.categorybtn);
        shoplistbtn = (Button) findViewById(R.id.shoplistbtn);
        loginbtn = (Button) findViewById(R.id.loginbtn);

        productView = (RecyclerView) findViewById(R.id.productView);
        productView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(layoutManager);

        productlist = new ArrayList<>();

        Intent intent = getIntent();

        category = intent.getExtras().getString("category");

        if (category.equals("쇼파")) {
            productlist.add(new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
            productlist.add(new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "new", 1));
        } else if (category.equals("침대")) {
            productlist.add(new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "event", 1));
            productlist.add(new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
        }

        ProductlistAdapter productlistAdapter = new ProductlistAdapter(productlist);
        productView.setAdapter(productlistAdapter);

        homebtn.setBackgroundColor(Color.rgb(213,213,213));
        categorybtn.setBackgroundColor(Color.rgb(160,186,237));
        shoplistbtn.setBackgroundColor(Color.rgb(213,213,213));
        loginbtn.setBackgroundColor(Color.rgb(213,213,213));
    }

    protected void onStart() {
        super.onStart();

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

    }
}