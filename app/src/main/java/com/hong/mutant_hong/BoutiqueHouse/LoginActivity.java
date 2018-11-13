package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    Button homebtn, categorybtn, shoplistbtn, loginbtn, searchbtn;

    EditText userid, userpw;

    Button tologin, tojoin;

    LinearLayout loginlayout, logintrue;

    Button logoutbtn;

    TextView userName;

    SharedPreferences userPrefs;
    SharedPreferences loginState;

    static boolean loginstate = false;
    static String user;

    static boolean logout = false;

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

        userName = (TextView)findViewById(R.id.userName);

        userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        loginState = getSharedPreferences("loginState", MODE_PRIVATE);

        if(loginstate == true) {
            loginlayout.setVisibility(View.GONE);
            logintrue.setVisibility(View.VISIBLE);

            String login = loginState.getString("login","0");

            if(!login.equals("0")){
                userName.setText(loginState.getString("login","0"));
                user = login;
            }
        }

        homebtn.setBackgroundColor(Color.rgb(213,213,213));
        categorybtn.setBackgroundColor(Color.rgb(213,213,213));
        shoplistbtn.setBackgroundColor(Color.rgb(213,213,213));
        loginbtn.setBackgroundColor(Color.rgb(160,186,237));
    }

    protected void onStart() {
        super.onStart();

        Log.d("LoginActivity", "onStart");

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        shoplistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoplistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                if(loginstate == false) {
                    Log.d("login","false");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                else {
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                }

                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInfo = userPrefs.getString(userid.getText().toString(),"0");

                //로그인 실패
                if(userInfo.equals("0")){
                    Toast.makeText(LoginActivity.this, "아이디 또는 패스워드가 맞지않습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(userInfo.equals(userpw.getText().toString())){
                    loginstate = true;
                    userName.setText(userid.getText());
                    user = userid.getText().toString();
                    loginlayout.setVisibility(View.GONE);
                    logintrue.setVisibility(View.VISIBLE);

                    //로그아웃 전까지 자동 로그인
                    SharedPreferences.Editor editor = loginState.edit();
                    editor.putString("login", userid.getText().toString());
                    editor.commit();

                    /*
                    //로그인 시 장바구니 동기화
                    try {

                        if(!ShoplistActivity.shoppinglist.isEmpty()){
                            SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
                            SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
                            Gson gson = new Gson();
                            String jsonShoppingList = gson.toJson(ShoplistActivity.shoppinglist);
                            Log.d("쇼핑리스트", jsonShoppingList);
                            shopPrefs_Editor.putString(user, jsonShoppingList);
                            shopPrefs_Editor.commit();
                        }

                    }catch (Exception e){

                    }
                    */
                }
            }
        });

        tojoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                finish();
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

                SharedPreferences.Editor editor = loginState.edit();
                editor.clear();
                editor.commit();

                logout = true;

                SharedPreferences shopPrefs = getSharedPreferences("shoppinglist", MODE_PRIVATE);
                SharedPreferences.Editor shopPrefs_Editor = shopPrefs.edit();
                Gson gson = new Gson();

                String json = gson.toJson(ShoplistActivity.shoppinglist);
                Log.d("user 쇼핑리스트 동기화", json);
                shopPrefs_Editor.putString(LoginActivity.user, json);
                shopPrefs_Editor.commit();

                ShoplistActivity.userlistFlag = false;

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("LoginActivity", "onResume");
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
