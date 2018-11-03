package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoplistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class ShoplistViewHolder extends RecyclerView.ViewHolder {

        ImageView prImg;

        TextView prName, prPrice, prAmont;
        public final View mView;

        ShoplistViewHolder(View view) {
            super(view);

            mView = view;

            prImg = view.findViewById(R.id.prImg);
            prName = view.findViewById(R.id.prName);
            prPrice = view.findViewById(R.id.prPrice);
            prAmont = view.findViewById(R.id.prAmount);
        }

    }

    private ArrayList<Product> shoppinglist;

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

        final ShoplistAdapter.ShoplistViewHolder myViewHolder = (ShoplistAdapter.ShoplistViewHolder) holder;

        myViewHolder.prImg.setImageResource(shoppinglist.get(position).drawableId);
        myViewHolder.prName.setText(shoppinglist.get(position).name);
        myViewHolder.prPrice.setText(Integer.toString(shoppinglist.get(position).price));
        myViewHolder.prAmont.setText(Integer.toString(shoppinglist.get(position).amount));


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
    }

    @Override
    public int getItemCount() {
        return shoppinglist.size();
    }
}
