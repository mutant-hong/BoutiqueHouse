package com.hong.mutant_hong.BoutiqueHouse;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class recommend {

    //파이어베이스
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("buy");

    ArrayList<String> recommendlist;
    ArrayList<Float> tanimoto;
    String[] buylist;
    String userName;

    public recommend(String userName){
        this.userName = userName;

        recommendlist = new ArrayList<>();
        tanimoto = new ArrayList<>();
    }

    public void readData(){
        //파이어베이스 데이터 불러오기
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!recommendlist.isEmpty())
                    recommendlist.clear();

                Log.d("user", userName);

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(!userName.equals(snapshot.getKey())){
                        String[] parsing = snapshot.getValue().toString().split("=구매");
                        String str = "";
                        for(int i = 0; i < parsing.length; i++){
                             str += parsing[i];
                        }
                        recommendlist.add(str);

                    }

                    else{
                        Log.d("buylist", snapshot.getValue().toString().substring(1, snapshot.getValue().toString().length()-1));
                        buylist = snapshot.getValue().toString().substring(1, snapshot.getValue().toString().length()-1).split("=구매");
                    }

                    Log.d("userName", " " + snapshot.getKey());

                    Log.d("data", " " + snapshot.getValue().toString());

                    //Log.d("test", snapshot.child(snapshot.getKey()).getValue().toString());

                    //productlist.add(new Search(snapshot.getKey(), Integer.parseInt(snapshot.getValue().toString())));
                }

                String str = "";
                for(int i = 0; i < buylist.length; i++){
                    str += buylist[i];
                }

                Log.d("Str", str);
                buylist = str.split(", ");

                compare();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void compare(){

        String[] split;

        //파싱하기
        for(int i = 0; i < recommendlist.size(); i++){

            recommendlist.set(i, recommendlist.get(i).substring(1, recommendlist.get(i).length()-1));
            Log.d("compare", recommendlist.get(i));
        }

        //유사도 측정
        float count = 0;

        for(int i = 0; i < recommendlist.size(); i++){
            count = 0;
            split = recommendlist.get(i).split(", ");

            for(int j = 0; j < buylist.length; j++){
                Log.d("buylist" + j, buylist[j]);
                for (int k = 0; k < split.length; k++){
                    if(buylist[j].equals(split[k])){
                        count++;
                        break;
                    }
                }
            }
            tanimoto.add( count / (Float)(buylist.length + split.length - count));
            Log.d("bunmo", (Float)(buylist.length + split.length - count) + "");
            Log.d("tanimoto", tanimoto.get(i) + "");
        }

        //가장 유사한 사용자 찾기
        float max = 0;
        int maxindex = 0;

        for(int i = 0; i < tanimoto.size(); i++){
            if(tanimoto.get(i) >= max){
                max = tanimoto.get(i);
                maxindex = i;
            }
        }
        Log.d("max", max + "");
        Log.d("maxindex", maxindex + "");
        String[] similar = recommendlist.get(maxindex).split(", ");
        boolean same = false;
        //가장 유사한 사용자와 나의 구매 리스트 비교, 없는 제품 추천
        for (int i = 0; i < similar.length; i++){
            same = false;
            for(int j = 0; j < buylist.length; j++){
                if(similar[i].equals(buylist[j])){
                    same = true;
                    break;
                }
            }
            if(same == false){
                ProductActivity.userRecommend.add(new Product(Product.productInfolist.get(similar[i]).drawableId,
                        Product.productInfolist.get(similar[i]).name,
                        Product.productInfolist.get(similar[i]).price,
                        Product.productInfolist.get(similar[i]).w,
                        Product.productInfolist.get(similar[i]).h,
                        Product.productInfolist.get(similar[i]).d,
                        Product.productInfolist.get(similar[i]).attribute,
                        Product.productInfolist.get(similar[i]).amount));
            }
        }

    }
}
