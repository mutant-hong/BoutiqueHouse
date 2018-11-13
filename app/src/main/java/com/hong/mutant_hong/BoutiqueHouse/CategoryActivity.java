package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;

    RecyclerView category;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Category> categorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        category = (RecyclerView)findViewById(R.id.category);
        category.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        category.setLayoutManager(layoutManager);

        categorylist = new ArrayList<>();
        categorylist.add(new Category("쇼파"));
        categorylist.add(new Category("침대"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categorylist);
        category.setAdapter(categoryAdapter);

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

    @Override
    protected void onPause() {
        super.onPause();
        //overridePendingTransition(0,0);
        //액티비티 애니메이션 x
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CategoryActivity", "onDestroy");
        //overridePendingTransition(0,0);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, 0);
    }
}
