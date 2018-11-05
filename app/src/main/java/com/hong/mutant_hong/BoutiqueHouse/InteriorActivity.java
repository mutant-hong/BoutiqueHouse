package com.hong.mutant_hong.BoutiqueHouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class InteriorActivity extends AppCompatActivity implements View.OnTouchListener {

    View view, view2;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_interior);

        view = (View)findViewById(R.id.view);
        view.setOnTouchListener(this);

        view2 = (View)findViewById(R.id.view2);
        view2.setOnTouchListener(this);
    }

    float oldXvalue;
    float oldYvalue;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = event.getX();
            oldYvalue = event.getY();
            //  Log.i("Tag1", "Action Down X" + event.getX() + "," + event.getY());
            Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            v.setX(event.getRawX() - oldXvalue);
            v.setY(event.getRawY() - (oldYvalue + v.getHeight()));
            //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if (v.getX() > width && v.getY() > height) {
                v.setX(width);
                v.setY(height);
                Log.d("1", "1");
            } else if (v.getX() < 0 && v.getY() > height) {
                v.setX(0);
                v.setY(height);
                Log.d("2", "2");
            } else if (v.getX() > width && v.getY() < 0) {
                v.setX(width);
                v.setY(0);
                Log.d("3", "3");
            } else if (v.getX() < 0 && v.getY() < 0) {
                v.setX(0);
                v.setY(0);
                Log.d("4", "4");
            } else if (v.getX() < 0 || v.getX() > width) {
                if (v.getX() < 0) {
                    v.setX(0);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                    Log.d("5", "5");
                } else {
                    v.setX(width);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                    Log.d("6", "6");
                }
            } else if (v.getY() < 0 || v.getY() > height) {
                if (v.getY() < 0) {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(0);
                    Log.d("7", "7");
                } else {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(height);
                    Log.d("8", "8");
                }
            }

            if (view.getX() > width && view.getY() > height) {
                view.setX(width);
                view.setY(height);
                Log.d("1", "1");
            } else if (view.getX() < 0 && view.getY() > height) {
                view.setX(0);
                view.setY(height);
                Log.d("2", "2");
            } else if (view.getX() > width && view.getY() < 0) {
                view.setX(width);
                view.setY(0);
                Log.d("3", "3");
            } else if (view.getX() < 0 && view.getY() < 0) {
                view.setX(0);
                view.setY(0);
                Log.d("4", "4");
            } else if (view.getX() < 0 || view.getX() > width) {
                if (view.getX() < 0) {
                    view.setX(0);
                    view.setY(event.getRawY() - oldYvalue - view.getHeight());
                    Log.d("5", "5");
                } else {
                    view.setX(width);
                    view.setY(event.getRawY() - oldYvalue - view.getHeight());
                    Log.d("6", "6");
                }
            } else if (view.getY() < 0 || view.getY() > height) {
                if (view.getY() < 0) {
                    view.setX(event.getRawX() - oldXvalue);
                    view.setY(0);
                    Log.d("7", "7");
                } else {
                    view.setX(event.getRawX() - oldXvalue);
                    view.setY(height);
                    Log.d("8", "8");
                }
            }

        }
        return true;
    }
}
