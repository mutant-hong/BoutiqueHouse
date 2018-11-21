package com.hong.mutant_hong.BoutiqueHouse;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRoom extends Dialog implements View.OnClickListener{

    EditText roomx, roomy, roomz;
    Button create;
    private Context context;

    public CreateRoom(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);

        roomx = (EditText)findViewById(R.id.roomx);
        roomy = (EditText)findViewById(R.id.roomy);
        roomz = (EditText)findViewById(R.id.roomz);
        create = (Button)findViewById(R.id.create);

        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!roomx.getText().toString().equals("") && !roomy.getText().toString().equals("") && !roomz.getText().toString().equals("")){
            InteriorActivity.x = Integer.parseInt(roomx.getText().toString());
            InteriorActivity.y = Integer.parseInt(roomy.getText().toString());
            InteriorActivity.z = Integer.parseInt(roomz.getText().toString());
        }

        if(InteriorActivity.x > 0 && InteriorActivity.y > 0 && InteriorActivity.y > 0) {
            InteriorActivity.move = true;
            cancel();
        }
    }
}
