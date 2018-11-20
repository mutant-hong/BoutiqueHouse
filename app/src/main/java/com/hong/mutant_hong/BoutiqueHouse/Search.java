package com.hong.mutant_hong.BoutiqueHouse;

public class Search implements Comparable<Search>{

    //검색어 순위
    String category;
    int count;

    public Search(String category, int count){
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return this.category;
    }

    public int getCount() {
        return this.count;
    }

    //검색어 순위 sorting
    @Override
    public int compareTo(Search s){
        if (this.count < s.getCount()) {
            return 1;
        } else if (this.count > s.getCount()) {
            return -1;
        }
        return 0;
    }
}
