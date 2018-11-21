package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        public final View mView;

        CategoryViewHolder(View view) {
            super(view);
            mView = view;

            item = view.findViewById(R.id.item);

        }

    }

    private ArrayList<Category> categorylist;

    CategoryAdapter(ArrayList<Category> categorylist){
        this.categorylist = categorylist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_categoryitem, parent, false);

        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final CategoryViewHolder myViewHolder = (CategoryViewHolder) holder;

        myViewHolder.item.setText(categorylist.get(position).category);

        myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                //Toast.makeText(context, myViewHolder.item.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProductlistActivity.class);
                intent.putExtra("category", myViewHolder.item.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }
}
