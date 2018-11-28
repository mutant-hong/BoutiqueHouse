package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BuyingActivity extends AppCompatActivity {


    EditText name, phone, adr2;
    static TextView adr;
    TextView total;
    Button searchAdr, buy, toHome;

    LinearLayout buying, buyed;

    static Address address;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_buying);

        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        adr2 = (EditText)findViewById(R.id.adr2);
        adr = (TextView) findViewById(R.id.adr);
        searchAdr = (Button)findViewById(R.id.searchAdr);
        total = (TextView) findViewById(R.id.total);
        buy = (Button)findViewById(R.id.buy);
        toHome = (Button)findViewById(R.id.toHome);

        buying = (LinearLayout) findViewById(R.id.buying);
        buyed = (LinearLayout)findViewById(R.id.buyed);

        address = new Address(this);

        Intent intent = getIntent();
        total.setText(intent.getExtras().getString("total") + " 원");

    }

    @Override
    protected void onStart() {
        super.onStart();

        searchAdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address.show();
            }
        });

        address.setCanceledOnTouchOutside(false);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buying.setVisibility(View.GONE);
                buyed.setVisibility(View.VISIBLE);
            }
        });

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

    }
}