package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    Button toShoplist, up, down;
    TextView prName, prPrice, prAmount;
    ImageView img;
    String name;
    int amount = 1;

    //제품 추천 리스트
    TextView recommendText;
    RecyclerView recommendView;
    RecyclerView.LayoutManager layoutManager;

    //애니메이션
    ScrollView prView;
    LinearLayout animView;
    ImageView animImage;

    Handler handler;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Log.d("Product", "onCreate");

        img = (ImageView)findViewById(R.id.img);
        prName = (TextView)findViewById(R.id.name);
        prPrice = (TextView) findViewById(R.id.price);
        prAmount = (TextView) findViewById(R.id.amount);
        toShoplist = (Button) findViewById(R.id.toShoplist);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);

        prView = (ScrollView) findViewById(R.id.prView);
        animView = (LinearLayout)findViewById(R.id.animView);
        animImage = (ImageView)findViewById(R.id.animimage);

        recommendText = (TextView)findViewById(R.id.recommendText);

        recommendView = (RecyclerView) findViewById(R.id.recommendView);
        recommendView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recommendView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");

        new Product().productInfo();


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.wave);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                prView.setVisibility(View.VISIBLE);
                animView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animImage.startAnimation(animation);

        //추천 리스트
        if(LoginActivity.loginstate) {
            recommend recommend = new recommend(LoginActivity.user);
            recommend.readData();

            ProductlistAdapter productlistAdapter = new ProductlistAdapter(recommend.userRecommend);
            recommendView.setAdapter(productlistAdapter);

            recommendText.setVisibility(View.VISIBLE);
            recommendView.setVisibility(View.VISIBLE);

        }

        else{
            recommendText.setVisibility(View.GONE);
            recommendView.setVisibility(View.GONE);
        }


        img.setImageResource(Product.productInfolist.get(name).drawableId);
        prName.setText(Product.productInfolist.get(name).name);
        prPrice.setText("￦ " + Integer.toString(Product.productInfolist.get(name).price) + "/개");

        //최근본항목
        if(MainActivity.list.size() == 0){
            Log.d("productfirst", "add");
            MainActivity.list.add(name);
        }

        Boolean same = false;

        for (int i = 0; i < MainActivity.list.size(); i++) {
            if(MainActivity.list.get(i).toString().equals(name)) {
                Log.d("productsame", "break");
                same = true;
                break;
            }
        }

        if(same == false){
            MainActivity.list.add(name);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prAmount.setText(Integer.toString(amount + 1));
                amount++;
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount <= 1){
                    Toast.makeText(ProductActivity.this, "최소 수량은 1 입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    prAmount.setText(Integer.toString(amount - 1));
                    amount--;
                }

            }
        });

        toShoplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("amount", amount);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        /*
        if(LoginActivity.loginstate) {
            recommendText.setVisibility(View.VISIBLE);
            recommendView.setVisibility(View.VISIBLE);

            recommend recommend = new recommend(LoginActivity.user);
            recommend.readData();

            ProductlistAdapter productlistAdapter = new ProductlistAdapter(userRecommend);
            recommendView.setAdapter(productlistAdapter);
        }

        else{
            recommendText.setVisibility(View.GONE);
            recommendView.setVisibility(View.GONE);
        }
        */
    }


}
