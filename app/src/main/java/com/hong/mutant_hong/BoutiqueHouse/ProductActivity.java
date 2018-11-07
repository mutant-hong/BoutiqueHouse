package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {

    Button toShoplist, up, down;
    TextView prName, prPrice, prAmount;
    ImageView img;
    String name;
    int amount = 1;
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

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");

        if(name.equals("landskrona")){
            img.setImageResource(R.drawable.landskrona);
            prName.setText(name);
            prPrice.setText("134000");
        }
        else if(name.equals("kivik")){
            img.setImageResource(R.drawable.kivik);
            prName.setText(name);
            prPrice.setText("289000");
        }
        else if(name.equals("tarva")){
            img.setImageResource(R.drawable.tarva);
            prName.setText(name);
            prPrice.setText("599000");
        }
        else if(name.equals("hemnnes")){
            img.setImageResource(R.drawable.hemnnes);
            prName.setText(name);
            prPrice.setText("1999000");
        }

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
            }
        });
    }
}
