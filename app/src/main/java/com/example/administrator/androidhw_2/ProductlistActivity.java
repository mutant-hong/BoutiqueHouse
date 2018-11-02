package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ProductlistActivity extends AppCompatActivity {

    RecyclerView productView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> productlist;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        productView = (RecyclerView)findViewById(R.id.productView);
        productView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(layoutManager);

        productlist = new ArrayList<>();

        Intent intent = getIntent();

        category = intent.getExtras().getString("category");

        if(category.equals("쇼파")){
            productlist.add(new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
            productlist.add(new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "normal", 1));
        }
        else if(category.equals("침대")){
            productlist.add(new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "normal", 1));
            productlist.add(new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
        }

        ProductlistAdapter productlistAdapter = new ProductlistAdapter(productlist);
        productView.setAdapter(productlistAdapter);
    }
}