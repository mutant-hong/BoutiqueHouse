package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class ShoplistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Product> shoppinglist;

    public class ShoplistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView prImg;
        TextView prName, prPrice, prAmount;
        EditText changeAmount;
        LinearLayout productitem;
        Button delbtn, changebtn;

        public final View mView;

        ShoplistViewHolder(View view) {
            super(view);

            mView = view;

            prImg = view.findViewById(R.id.prImg);
            prName = view.findViewById(R.id.prName);
            prPrice = view.findViewById(R.id.prPrice);
            prAmount = view.findViewById(R.id.prAmount);
            delbtn = view.findViewById(R.id.delbtn);
            productitem = view.findViewById(R.id.productitem);
            changeAmount = view.findViewById(R.id.changeAmount);
            changebtn = view.findViewById(R.id.changebtn);

            productitem.setOnClickListener(this);
            delbtn.setOnClickListener(this);
            prAmount.setOnClickListener(this);
            changebtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            switch(v.getId()){

                case R.id.productitem:

                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("name", prName.getText().toString());
                    context.startActivity(intent);

                    break;

                case R.id.delbtn:

                    try {

                        shoppinglist.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());

                        Log.d("삭제", "삭제");
                        Log.d("position", Integer.toString(getAdapterPosition()));
                        //액티비티 변수에 적용
                        //ShoplistActivity.shoppinglist.remove(getAdapterPosition());
                        ShoplistActivity.changeItem = true;

                        intent = new Intent(context, ShoplistActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent);

                    }catch (IndexOutOfBoundsException ex){
                        ex.printStackTrace();
                    }

                    break;

                case R.id.prAmount:

                    changeAmount.setVisibility(View.VISIBLE);
                    changebtn.setVisibility(View.VISIBLE);
                    prAmount.setVisibility(View.GONE);
                    delbtn.setVisibility(View.GONE);

                    changeAmount.setText(prAmount.getText().toString());

                    break;

                case R.id.changebtn:

                    changeAmount.setVisibility(View.GONE);
                    changebtn.setVisibility(View.GONE);
                    prAmount.setVisibility(View.VISIBLE);
                    delbtn.setVisibility(View.VISIBLE);

                    prAmount.setText(changeAmount.getText().toString());

                    //리스트에 적용
                    shoppinglist.set(getAdapterPosition(), new Product(shoppinglist.get(getAdapterPosition()).drawableId, shoppinglist.get(getAdapterPosition()).name,
                            shoppinglist.get(getAdapterPosition()).price, shoppinglist.get(getAdapterPosition()).w,  shoppinglist.get(getAdapterPosition()).h,
                            shoppinglist.get(getAdapterPosition()).d, shoppinglist.get(getAdapterPosition()).attribute, Integer.parseInt(prAmount.getText().toString())));

                    notifyItemChanged(getAdapterPosition());

                    //액티비티 변수에 적용

                    ShoplistActivity.shoppinglist.set(getAdapterPosition(), new Product(shoppinglist.get(getAdapterPosition()).drawableId, shoppinglist.get(getAdapterPosition()).name,
                            shoppinglist.get(getAdapterPosition()).price, shoppinglist.get(getAdapterPosition()).w,  shoppinglist.get(getAdapterPosition()).h,
                            shoppinglist.get(getAdapterPosition()).d, shoppinglist.get(getAdapterPosition()).attribute, Integer.parseInt(prAmount.getText().toString())));

                    ShoplistActivity.changeItem = true;

                    intent = new Intent(context, ShoplistActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);

                    break;
            }
        }
    }

    ShoplistAdapter(ArrayList<Product> shoppinglist){
        this.shoppinglist = shoppinglist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shoplistitem, parent, false);

        return new ShoplistAdapter.ShoplistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ShoplistAdapter.ShoplistViewHolder myViewHolder = (ShoplistAdapter.ShoplistViewHolder) holder;

        myViewHolder.prImg.setImageResource(shoppinglist.get(position).drawableId);
        myViewHolder.prName.setText(shoppinglist.get(position).name);
        myViewHolder.prPrice.setText("￦ " + Integer.toString(shoppinglist.get(position).price) + "/개");
        myViewHolder.prAmount.setText(Integer.toString(shoppinglist.get(position).amount));

        /*
        //해당 제품 페이지 이동
        myViewHolder.productitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                //Toast.makeText(context, myViewHolder.prName.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("name", myViewHolder.prName.getText());

                context.startActivity(intent);
            }
        });

        //장바구니에서 아이템 삭제
        myViewHolder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                //shoppinglist.remove(position);

                //삭제적용
                notifyItemRemoved(position);
                //notifyItemRangeChanged(position, getItemCount());

                //액티비티 변수에 적용
                //ShoplistActivity.shoppinglist.remove(position);

                ShoplistActivity.changeItem = true;
            }
        });

        myViewHolder.prAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.changeAmount.setVisibility(View.VISIBLE);
                myViewHolder.changebtn.setVisibility(View.VISIBLE);
                myViewHolder.prAmount.setVisibility(View.GONE);
                myViewHolder.delbtn.setVisibility(View.GONE);

                myViewHolder.changeAmount.setText(myViewHolder.prAmount.getText().toString());
            }
        });

        myViewHolder.changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.changeAmount.setVisibility(View.GONE);
                myViewHolder.changebtn.setVisibility(View.GONE);
                myViewHolder.prAmount.setVisibility(View.VISIBLE);
                myViewHolder.delbtn.setVisibility(View.VISIBLE);

                myViewHolder.prAmount.setText(myViewHolder.changeAmount.getText().toString());

                //리스트에 적용
                shoppinglist.set(position, new Product(shoppinglist.get(position).drawableId, shoppinglist.get(position).name,
                        shoppinglist.get(position).price, shoppinglist.get(position).w,  shoppinglist.get(position).h,
                        shoppinglist.get(position).d, shoppinglist.get(position).attribute, Integer.parseInt(myViewHolder.prAmount.getText().toString())));

                notifyItemChanged(position);

                //액티비티 변수에 적용
                ShoplistActivity.shoppinglist.set(position, new Product(shoppinglist.get(position).drawableId, shoppinglist.get(position).name,
                        shoppinglist.get(position).price, shoppinglist.get(position).w,  shoppinglist.get(position).h,
                        shoppinglist.get(position).d, shoppinglist.get(position).attribute, Integer.parseInt(myViewHolder.prAmount.getText().toString())));

                ShoplistActivity.changeItem = true;
            }
        });

        myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(context, myViewHolder.prName.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("name", myViewHolder.prName.getText());

                context.startActivity(intent);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return shoppinglist.size();
    }

    public void changeItem(){
    }
}
