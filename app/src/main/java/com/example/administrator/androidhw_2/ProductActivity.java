package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    Button toShoplist;
    TextView text;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Log.d("Product", "onCreate");

        text = (TextView) findViewById(R.id.product);

        Intent intent = getIntent();

        name = intent.getExtras().getString("category");

        text.setText(name);

        toShoplist = (Button) findViewById(R.id.toShoplist);

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

        toShoplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.putExtra("name", name);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}
