package com.hong.mutant_hong.BoutiqueHouse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> searchlist;

    public class SearchlistViewHolder extends RecyclerView.ViewHolder {

        TextView num, prName;

        View mView;

        SearchlistViewHolder(View view) {
            super(view);
            mView = view;

            num = view.findViewById(R.id.num);
            prName = view.findViewById(R.id.prName);
        }
    }

    SearchlistAdapter(ArrayList<String> searchlist){
        this.searchlist = searchlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_searchlistitem, parent, false);

        return new SearchlistAdapter.SearchlistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SearchlistAdapter.SearchlistViewHolder myViewHolder = (SearchlistViewHolder) holder;

        myViewHolder.num.setText(Integer.toString(position + 1));
        myViewHolder.prName.setText(searchlist.get(position));
    }

    @Override
    public int getItemCount() {
        return searchlist.size();
    }
}
