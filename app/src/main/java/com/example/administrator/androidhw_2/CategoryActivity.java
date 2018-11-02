package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity {

    static final  String[] category_main = {"거실 가구", "주방 가구", "침실가구", "욕실 가구"};
    static final  String[] category_living = {"쇼파", "장식장"};
    static final  String[] category_kitchen = {"식탁", "의자"};
    static final  String[] category_bed = {"옷장", "책상", "침대"};

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;

    Button c1, c2, c3, c4;

    Button s1, s2;

    LinearLayout clayout, slayout;

    RecyclerView category1;
    RecyclerView.LayoutManager layoutManager1;
    ArrayList<Category> categorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        c1 = (Button)findViewById(R.id.c1);
        c2 = (Button)findViewById(R.id.c2);
        c3 = (Button)findViewById(R.id.c3);
        c4 = (Button)findViewById(R.id.c4);

        s1 = (Button)findViewById(R.id.s1);
        s2 = (Button)findViewById(R.id.s2);

        slayout = (LinearLayout)findViewById(R.id.slayout);
        clayout = (LinearLayout)findViewById(R.id.clayout);

        category1 = (RecyclerView)findViewById(R.id.category1);
        category1.setHasFixedSize(true);

        layoutManager1 = new LinearLayoutManager(this);
        category1.setLayoutManager(layoutManager1);

        categorylist = new ArrayList<>();
        categorylist.add(new Category("쇼파"));
        categorylist.add(new Category("침대"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categorylist);
        category1.setAdapter(categoryAdapter);

    }

    protected void onStart() {
        super.onStart();

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "homebtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "categorybtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "shoplistbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "loginbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String category =  c1.getText().toString();

                slayout.setVisibility(View.VISIBLE);
                clayout.setVisibility(View.GONE);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String category =  c2.getText().toString();

                //Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                //intent.putExtra("category", category);
                //startActivity(intent);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String category =  c3.getText().toString();

                //Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                //intent.putExtra("category", category);
                //startActivity(intent);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String category =  c4.getText().toString();

                //Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                //intent.putExtra("category", category);
                //startActivity(intent);
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category =  s1.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
                finish();
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category =  s2.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CategoryActivity", "onDestroy");
    }
}
