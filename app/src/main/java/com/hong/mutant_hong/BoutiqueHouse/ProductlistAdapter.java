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

public class ProductlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class ProductlistViewHolder extends RecyclerView.ViewHolder {

        ImageView prImg;

        TextView prName, prPrice;
        public final View mView;

        ProductlistViewHolder(View view) {
            super(view);

            mView = view;

            prImg = view.findViewById(R.id.prImg);
            prName = view.findViewById(R.id.prName);
            prPrice = view.findViewById(R.id.prPrice);

        }

    }

    private ArrayList<Product> productlist;

    ProductlistAdapter(ArrayList<Product> productlist){
        this.productlist = productlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productlistitem, parent, false);

        return new ProductlistAdapter.ProductlistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ProductlistAdapter.ProductlistViewHolder myViewHolder = (ProductlistAdapter.ProductlistViewHolder) holder;

        myViewHolder.prImg.setImageResource(productlist.get(position).drawableId);
        myViewHolder.prName.setText(productlist.get(position).name);
        myViewHolder.prPrice.setText(Integer.toString(productlist.get(position).price));

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
        return productlist.size();
    }
}
