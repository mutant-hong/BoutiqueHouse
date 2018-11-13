package com.hong.mutant_hong.BoutiqueHouse;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class InteriorActivity extends AppCompatActivity implements View.OnTouchListener {

    LinearLayout listArea;
    RelativeLayout interiorArea;

    boolean move = false;

    private static final String IMAGEVIEW_TAG = "드래그 이미지";

    int index = 0;
    ArrayList<String> viewID_List;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_interior);

        //view = (View)findViewById(R.id.view);
        //view2 = (View)findViewById(R.id.view);

        listArea = (LinearLayout)findViewById(R.id.listArea);
        interiorArea = (RelativeLayout)findViewById(R.id.interiorArea);

        findViewById(R.id.listArea).setOnDragListener(new DragListener());
        findViewById(R.id.interiorArea).setOnDragListener(new DragListener());

        showlist();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("InteriorActivity", "onStart");

        //view.setOnTouchListener(this);
        //view2.setOnTouchListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("InteriorActivity", "onResume");

        //view.setOnTouchListener(this);
        //view2.setOnTouchListener(this);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("InteriorActivity", "onRestart");

        //view.setOnTouchListener(this);
        //view2.setOnTouchListener(this);
    }

    float oldXvalue;
    float oldYvalue;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            //레이아웃 가로세로
            int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
            int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();


            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                oldXvalue = event.getX();
                oldYvalue = event.getY();

                Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());

                Log.d("child", Integer.toString(((ViewGroup) v.getParent()).getChildAt(0).getId()));
                Log.d("child", Float.toString(((ViewGroup) v.getParent()).getChildAt(0).getX()));
                //Log.d("child", Integer.toString(((ViewGroup) v.getParent()).getChildAt(1).getId()));
                //Log.d("child", Integer.toString(((ViewGroup) v.getParent()).getChildAt(2).getId()));
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

                try {

                    for (int i = 0; i <= index; i++) {

                        Log.d("dd", Float.toString(v.getX()));
                        Log.d("ss", Float.toString(((ViewGroup) v.getParent()).getChildAt(i).getX()));
                        Log.d("ee", Float.toString(((ViewGroup) v.getParent()).getChildAt(i).getX()));
                        //if(v.getId() != i) {
                        if (v.getX() > ((ViewGroup) v.getParent()).getChildAt(i).getX()
                                && v.getX() < ((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth()
                                && v.getY() > ((ViewGroup) v.getParent()).getChildAt(i).getY()
                                && v.getY() < ((ViewGroup) v.getParent()).getChildAt(i).getY() + ((ViewGroup) v.getParent()).getChildAt(i).getHeight()) {

                            Log.d("view겹침", "1");
                            v.setX(((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth());
                            v.setY(((ViewGroup) v.getParent()).getChildAt(i).getY());

                        } else if (v.getX() > ((ViewGroup) v.getParent()).getChildAt(i).getX()
                                && v.getX() < ((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth()
                                && v.getY() + v.getHeight() > ((ViewGroup) v.getParent()).getChildAt(i).getY()
                                && v.getY() + v.getHeight() < ((ViewGroup) v.getParent()).getChildAt(i).getY() + ((ViewGroup) v.getParent()).getChildAt(i).getHeight()) {

                            Log.d("view겹침", "2");
                            v.setX(((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth());
                            v.setY(((ViewGroup) v.getParent()).getChildAt(i).getY());

                        } else if (v.getX() + v.getWidth() > ((ViewGroup) v.getParent()).getChildAt(i).getX()
                                && v.getX() + v.getWidth() < ((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth()
                                && v.getY() + v.getHeight() > ((ViewGroup) v.getParent()).getChildAt(i).getY()
                                && v.getY() + v.getHeight() < ((ViewGroup) v.getParent()).getChildAt(i).getY() + ((ViewGroup) v.getParent()).getChildAt(i).getHeight()) {

                            Log.d("view겹침", "3");
                            v.setX(((ViewGroup) v.getParent()).getChildAt(i).getX() - v.getWidth());
                            v.setY(((ViewGroup) v.getParent()).getChildAt(i).getY());

                        } else if (v.getX() + v.getWidth() > ((ViewGroup) v.getParent()).getChildAt(i).getX()
                                && v.getX() + v.getWidth() < ((ViewGroup) v.getParent()).getChildAt(i).getX() + ((ViewGroup) v.getParent()).getChildAt(i).getWidth()
                                && v.getY() > ((ViewGroup) v.getParent()).getChildAt(i).getY()
                                && v.getY() < ((ViewGroup) v.getParent()).getChildAt(i).getY() + ((ViewGroup) v.getParent()).getChildAt(i).getHeight()) {

                            Log.d("view겹침", "4");
                            v.setX(((ViewGroup) v.getParent()).getChildAt(i).getX() - v.getWidth());
                            v.setY(((ViewGroup) v.getParent()).getChildAt(i).getY());

                        }

                    }

                } catch (Exception e) {

                }
            }

        return true;
    }

    private void showlist(){
        final int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        final int margin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        viewID_List = new ArrayList<>();

        for (int i = 0; i < ShoplistActivity.shoppinglist.size(); i++){

            for(int j = 0; j < ShoplistActivity.shoppinglist.get(i).amount ; j++){
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));

                if(ShoplistActivity.shoppinglist.get(i).name.equals("hemnnes")){
                    imageView.setImageResource(R.drawable.hemnnes);
                }
                else if(ShoplistActivity.shoppinglist.get(i).name.equals("kivik")){
                    imageView.setImageResource(R.drawable.kivik);
                }
                else if(ShoplistActivity.shoppinglist.get(i).name.equals("landskrona")){
                    imageView.setImageResource(R.drawable.landskrona);
                }
                else if(ShoplistActivity.shoppinglist.get(i).name.equals("tarva")){
                    imageView.setImageResource(R.drawable.tarva);
                }

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                params.setMargins(margin, margin, margin, margin);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setId(index);
                viewID_List.add(Integer.toString(index));
                imageView.setTag(IMAGEVIEW_TAG);

                imageView.setOnLongClickListener(new LongClickListener());

                listArea.addView(imageView);

                index++;
            }
        }
    }

    private class LongClickListener implements View.OnLongClickListener {

        public boolean onLongClick(View view) {

            // 태그 생성
            ClipData.Item item = new ClipData.Item(
                    (CharSequence) view.getTag());

            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(),
                    mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);

            view.startDrag(data, // data to be dragged
                    shadowBuilder, // drag shadow
                    view, // 드래그 드랍할  Vew
                    0 // 필요없은 플래그
            );

            view.setVisibility(View.INVISIBLE);
            return false;
        }
    }

    class DragListener implements View.OnDragListener {

        public boolean onDrag(View v, DragEvent event) {

            // 이벤트 시작
            switch (event.getAction()) {

                // 이미지를 드래그 시작될때
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED");
                    break;

                // 드래그한 이미지를 옮길려는 지역으로 들어왔을때
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED");
                    break;

                // 드래그한 이미지가 영역을 빠져 나갈때
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED");
                    break;

                // 이미지를 드래그해서 드랍시켰을때
                case DragEvent.ACTION_DROP:
                    Log.d("DragClickListener", "ACTION_DROP");

                    if (v == findViewById(R.id.interiorArea)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view
                                .getParent();
                        viewgroup.removeView(view);

                        Log.d("현재", "inte");

                        RelativeLayout containView = (RelativeLayout) v;
                        view.setOnTouchListener(InteriorActivity.this);
                        Log.d("id",Integer.toString(view.getId()));
                        containView.addView(view);
                        view.setVisibility(View.VISIBLE);

                    }else if (v == findViewById(R.id.listArea)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view
                                .getParent();
                        viewgroup.removeView(view);

                        Log.d("현재", "lis");

                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view);
                        view.setVisibility(View.VISIBLE);

                    }else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = getApplicationContext();
                        Toast.makeText(context,
                                "이미지를 다른 지역에 드랍할수 없습니다.",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }
    }
}
