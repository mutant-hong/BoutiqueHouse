package com.hong.mutant_hong.BoutiqueHouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class AddressViewHolder extends RecyclerView.ViewHolder {

        TextView adr;
        public final View mView;

        AddressViewHolder(View view) {
            super(view);

            mView = view;

            adr = view.findViewById(R.id.adr);


        }

    }

    private ArrayList<String> addresslist;

    //private Hashtable<String, String> addresslist;

    AddressAdapter(ArrayList<String> addresslist){
        this.addresslist = addresslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_addressitem, parent, false);

        return new AddressAdapter.AddressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final AddressAdapter.AddressViewHolder myViewHolder = (AddressAdapter.AddressViewHolder) holder;

        myViewHolder.adr.setText(addresslist.get(position));
        Log.d("position", "" + position);

        myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyingActivity.address.cancel();

                BuyingActivity.adr.setText(addresslist.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresslist.size();
    }
}
