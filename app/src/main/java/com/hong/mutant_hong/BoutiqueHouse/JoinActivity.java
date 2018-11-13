package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import java.util.Arrays;

public class JoinActivity extends AppCompatActivity {

    //private Context mContext;

    Button myfacebook_btn, userID_confirm, completebtn;

    TextView facebookstate;

    EditText userID, userPW, userPW_r;

    private FacebookLogin mLoginCallback;
    private CallbackManager mCallbackManager;

    SharedPreferences userPrefs;
    SharedPreferences.Editor prefsEditor;

    boolean nouser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //mContext = getApplicationContext();

        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookLogin();

        myfacebook_btn = (Button) findViewById(R.id.myfacebook_btn);

        userID_confirm = (Button) findViewById(R.id.userID_confirm);
        completebtn = (Button) findViewById(R.id.completebtn);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        userPW_r = (EditText) findViewById(R.id.userPW_r);

        facebookstate = (TextView) findViewById(R.id.facebookstate);

        userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        prefsEditor = userPrefs.edit();
    }

    @Override

    protected void onStart(){
        super.onStart();

        myfacebook_btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(JoinActivity.this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(mCallbackManager, mLoginCallback);
            }

        });

        completebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nouser == true && userPW.getText().toString().equals(userPW_r.getText().toString()) && facebookstate.getText().toString().equals("인증 완료")){

                    //페북 인증 후 로그아웃
                    LoginManager.getInstance().logOut();

                    //회원 정보 저장
                    prefsEditor.putString(userID.getText().toString(), userPW.getText().toString());
                    prefsEditor.commit();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                else{
                    Log.d("nouser", Boolean.toString(nouser));
                    Log.d("pw", userPW.getText().toString() + " / " + userPW_r.getText());
                    Log.d("facebook", facebookstate.getText().toString());
                    Toast.makeText(JoinActivity.this, "본인인증 및 회원정보를 수정해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        userID_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPrefs.getString(userID.getText().toString(),"0").equals("0")){
                    nouser = true;
                    Toast.makeText(JoinActivity.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    nouser = false;
                    Toast.makeText(JoinActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        facebookstate.setText("인증 완료");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("JoinActivity", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("JoinActivity", "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("JoinActivity", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("JoinActivity", "onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("JoinActivity", "onRestart");
    }
}
