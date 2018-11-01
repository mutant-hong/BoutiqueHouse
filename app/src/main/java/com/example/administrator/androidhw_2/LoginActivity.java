package com.example.administrator.androidhw_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;

    EditText userid, userpw;

    Button tologin, tojoin;

    LinearLayout loginlayout, logintrue;

    Button logoutbtn;

    static boolean loginstate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("LoginActivity", "onCreate");

        homebtn = (Button)findViewById(R.id.homebtn);
        categorybtn = (Button)findViewById(R.id.categorybtn);
        shoplistbtn = (Button)findViewById(R.id.shoplistbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);

        userid = (EditText)findViewById(R.id.userID);
        userpw = (EditText)findViewById(R.id.userPW);

        tologin = (Button)findViewById(R.id.tologin);
        tojoin = (Button)findViewById(R.id.tojoin);

        loginlayout = (LinearLayout)findViewById(R.id.loginlayout);
        logintrue = (LinearLayout)findViewById(R.id.logintrue);

        logoutbtn = (Button)findViewById(R.id.logoutbtn);

        if(loginstate == true) {
            loginlayout.setVisibility(View.GONE);
            logintrue.setVisibility(View.VISIBLE);
        }
    }

    protected void onStart() {
        super.onStart();

        Log.d("LoginActivity", "onStart");

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "homebtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "categorybtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "shoplistbtn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "loginbtn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                if(loginstate == false) {
                    Log.d("login","false");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                else {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }

                startActivity(intent);
            }
        });

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid.getText().toString().equals("teamnova") && userpw.getText().toString().equals("1234")){
                    //로그인 성공

                    loginstate = true;
                    loginlayout.setVisibility(View.GONE);
                    logintrue.setVisibility(View.VISIBLE);
                }

                else{
                    //로그인 실패
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                loginlayout.setVisibility(View.VISIBLE);
                logintrue.setVisibility(View.GONE);
                loginstate = false;
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("ShoplistActivity", "onResume");



    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("LoginActivity", "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LoginActivity", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LoginActivity", "onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("LoginActivity", "onRestart");

    }
}
