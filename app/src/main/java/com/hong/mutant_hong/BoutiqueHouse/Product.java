package com.hong.mutant_hong.BoutiqueHouse;

import java.util.Hashtable;

public class Product {

    public int drawableId;

    public String name;

    public int price, w, h, d, amount;

    public String attribute;

    static Hashtable<String, Product> productInfolist;

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

    public Product(){
        productInfolist = new Hashtable<>();
    }

    public void productInfo(){
        //쇼파
        productInfolist.put("landskrona", new Product(R.drawable.landskrona, "landskrona", 599000, 204, 89, 78, "new", 1));
        productInfolist.put("kivik", new Product(R.drawable.kivik, "kivik", 1999000, 328, 257, 83, "new", 1));
        productInfolist.put("klippan", new Product(R.drawable.klippan, "klippan", 169000, 138, 87, 67, "normal", 1));
        productInfolist.put("brathult", new Product(R.drawable.brathult, "brathult", 299000, 228, 138, 83, "normal", 1));
        productInfolist.put("ektorp", new Product(R.drawable.ektorp, "ektorp", 1699000, 138, 87, 67, "normal", 1));
        productInfolist.put("holarna", new Product(R.drawable.holarna, "holarna", 1699000, 138, 87, 67, "normal", 1));
        productInfolist.put("lidhult", new Product(R.drawable.lidhult, "lidhult", 1899000, 155, 93, 83, "normal", 1));
        productInfolist.put("stocksund", new Product(R.drawable.stocksund, "stocksund", 2099000, 155, 93, 83, "normal", 1));

        //침대
        productInfolist.put("tarva",new Product(R.drawable.tarva, "tarva", 134000, 128, 209, 124, "event", 1));
        productInfolist.put("hemnnes",new Product(R.drawable.hemnnes, "hemnnes", 289000, 154, 211, 188, "hot", 1));
        productInfolist.put("songesand",new Product(R.drawable.songesand, "songesand", 159000, 133, 207, 136, "normal", 1));
        productInfolist.put("glimsbu",new Product(R.drawable.glimsbu, "glimsbu", 189000, 154, 211, 188, "normal", 1));

        //어린이침대
        productInfolist.put("mydal",new Product(R.drawable.mydal, "mydal", 389000, 154, 211, 335, "normal", 1));
        productInfolist.put("slakt",new Product(R.drawable.slakt, "slakt", 179000, 140, 188, 136, "normal", 1));
        productInfolist.put("svarta",new Product(R.drawable.svarta, "svarta", 139000, 154, 211, 188, "normal", 1));

        //의자
        productInfolist.put("renberget",new Product(R.drawable.renberget, "renberget", 59900, 59, 65, 108, "normal", 1));
        productInfolist.put("janinge",new Product(R.drawable.janinge, "janinge", 49900, 50, 46, 76, "normal", 1));
        productInfolist.put("kaustby",new Product(R.drawable.kaustby, "kaustby", 49900, 44, 48, 103, "normal", 1));
        productInfolist.put("ingolf",new Product(R.drawable.ingolf, "ingolf", 79900, 59, 65, 108, "normal", 1));
        productInfolist.put("norraryd",new Product(R.drawable.norraryd, "norraryd", 69900, 50, 46, 76, "normal", 1));
        productInfolist.put("odger",new Product(R.drawable.odger, "odger", 69900, 44, 48, 103, "normal", 1));

        //거실수납
        productInfolist.put("billy",new Product(R.drawable.billy, "billy", 149000, 78, 41, 95, "normal", 1));
        productInfolist.put("fredde",new Product(R.drawable.fredde, "fredde", 289000, 78, 41, 195, "normal", 1));
        productInfolist.put("lixhult",new Product(R.drawable.lixhult, "lixhult", 198000, 58, 41, 88, "normal", 1));
        productInfolist.put("malsjo",new Product(R.drawable.malsjo, "malsjo", 339000, 88, 41, 105, "normal", 1));
        productInfolist.put("svalnas",new Product(R.drawable.svalnas, "svalnas", 198000, 58, 41, 885, "normal", 1));
        productInfolist.put("vittsjo",new Product(R.drawable.vittsjo, "vittsjo", 249000, 158, 41, 105, "normal", 1));

        //침실수납
        productInfolist.put("askvoll",new Product(R.drawable.askvoll, "askvoll", 269000, 98, 51, 225, "normal", 1));
        productInfolist.put("bekant",new Product(R.drawable.bekant, "bekant", 379000, 105, 51, 225, "normal", 1));
        productInfolist.put("brimnes",new Product(R.drawable.brimnes, "brimnes", 229000, 105, 51, 125, "normal", 1));
        productInfolist.put("malm",new Product(R.drawable.malm, "malm", 345000, 120, 51, 125, "normal", 1));

        //테이블
        productInfolist.put("ekedalen",new Product(R.drawable.ekedalen, "ekedalen", 169000, 125, 125, 140, "normal", 1));
        productInfolist.put("fanom",new Product(R.drawable.fanom, "fanom", 99000, 125, 125, 140, "normal", 1));
        productInfolist.put("ryggestad",new Product(R.drawable.ryggestad, "ryggestad", 125000, 125, 125, 140, "normal", 1));
        productInfolist.put("torsby",new Product(R.drawable.torsby, "torsby", 88000, 125, 125, 140, "normal", 1));

        //책상
        productInfolist.put("micke",new Product(R.drawable.micke, "micke", 125000, 125, 85, 100, "normal", 1));
        productInfolist.put("pahl",new Product(R.drawable.pahl, "pahl", 98000, 125, 85, 100, "normal", 1));

        //어린이책상
        productInfolist.put("beant",new Product(R.drawable.beant, "beant", 105000, 115, 75, 85, "normal", 1));
        productInfolist.put("flisat",new Product(R.drawable.flisat, "flisat", 128000, 115, 75, 85, "normal", 1));

        //주방수납
        productInfolist.put("liatorp",new Product(R.drawable.liatorp, "liatorp", 569000, 125, 85, 220, "normal", 1));
        productInfolist.put("malso",new Product(R.drawable.malso, "malso", 399000, 125, 90, 120, "normal", 1));
        productInfolist.put("regossor",new Product(R.drawable.regossor, "regossor", 425000, 125, 85, 190, "normal", 1));

        //욕실거울수납
        productInfolist.put("godmorgon",new Product(R.drawable.godmorgon, "godmorgon", 169000, 65, 20, 80, "normal", 1));
        productInfolist.put("isberget",new Product(R.drawable.isberget, "isberget", 199000, 65, 20, 80, "normal", 1));
        productInfolist.put("lillangen",new Product(R.drawable.lillangen, "lillangen", 125000, 65, 20, 80, "normal", 1));

        //욕실수납
        productInfolist.put("brusali",new Product(R.drawable.brusali, "brusali", 89000, 85, 60, 205, "normal", 1));
        productInfolist.put("dynan",new Product(R.drawable.dynan, "dynan", 99000, 85, 80, 205, "normal", 1));

        //조명
        productInfolist.put("foto",new Product(R.drawable.foto, "foto", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("ldasen",new Product(R.drawable.ldasen, "ldasen", 19000, 0, 0, 0, "normal", 1));
        productInfolist.put("ranarp",new Product(R.drawable.ranarp, "ranarp", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("stakra",new Product(R.drawable.stakra, "stakra", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("strala",new Product(R.drawable.strala, "strala", 19000, 0, 0, 0, "normal", 1));
        productInfolist.put("vindkare",new Product(R.drawable.vindkare, "vindkare", 69900, 0, 0, 0, "normal", 1));

        //러그
        productInfolist.put("adum",new Product(R.drawable.adum, "adum", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hampen",new Product(R.drawable.hampen, "hampen", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hodde",new Product(R.drawable.hodde, "hodde", 79900, 0, 0, 0, "normal", 1));
        productInfolist.put("kristrup",new Product(R.drawable.kristrup, "kristrup", 79900, 0, 0, 0, "normal", 1));
        productInfolist.put("sivested",new Product(R.drawable.sivested, "sivested", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("stockholm",new Product(R.drawable.stockholm, "stockholm", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("trampa",new Product(R.drawable.trampa, "trampa", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("vinter",new Product(R.drawable.vinter, "vinter", 49900, 0, 0, 0, "normal", 1));

        //커튼
        productInfolist.put("glansnava",new Product(R.drawable.glansnava, "glansnava", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("hilja",new Product(R.drawable.hilja, "hilja", 49900, 0, 0, 0, "normal", 1));
        productInfolist.put("lejongap",new Product(R.drawable.lejongap, "lejongap", 49900, 0, 0, 0, "normal", 1));
        productInfolist.put("lisavritt",new Product(R.drawable.lisavritt, "lisavritt", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("merete",new Product(R.drawable.merete, "merete", 69900, 0, 0, 0, "normal", 1));
        productInfolist.put("vilvorg",new Product(R.drawable.vilvorg, "stockholm", 59900, 0, 0, 0, "normal", 1));
        productInfolist.put("vivan",new Product(R.drawable.vivan, "vivan", 49900, 0, 0, 0, "normal", 1));

    }
}
