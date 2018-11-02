package com.example.administrator.androidhw_2;

public class Product {

    public int drawableId;

    public String name;

    public int price, w, h, d, amount;

    public String attribute;

    public Product(int drawableId, String name, int price, int w, int h, int d, String attribute, int amount){
        this.drawableId = drawableId;
        this.name = name;
        this.price = price;
        this.w = w;
        this.h = h;
        this.d = d;
        this.attribute = attribute;
        this.amount = amount;
    }
}
