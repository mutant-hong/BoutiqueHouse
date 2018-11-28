package com.hong.mutant_hong.BoutiqueHouse;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Address extends Dialog implements View.OnClickListener{

    EditText adr;
    Button search;
    private Context context;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> addresslist ;
    ArrayList<String> juso;
    static ArrayList<String> num;

    String address;

    public Address(@NonNull Context context) {
        super(context);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        adr = (EditText)findViewById(R.id.adr);
        search = (Button)findViewById(R.id.search);

        recyclerView = (RecyclerView) findViewById(R.id.adrlist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // 주소 목록 보여주기

        address = adr.getText().toString();

        new AsycTask().execute();

        //cancel();
    }

    public class AsycTask extends AsyncTask<Object, Void, String> {

        Elements adrs;
        Elements adrNum;
        Document doc = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Object... params) {

            try {
                //사이트를 불러옴

                Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=" +
                        address + "+우편번호&oquery=" + address + "+주소&tqi=T%2FGTxwpVuEGssaMnlJlssssssoV-433831").method(Connection.Method.GET).execute();

                doc = response.parse();

                String html = doc.toString();
                String text = doc.text();

                Log.d("text", text);
                Log.d("html", html);

                //주소
                adrs = doc.select("span.r_addr");
                Log.d("adrs", adrs.text());

                //우편번호
                adrNum = doc.select("td.tc");
                Log.d("adrNum", adrNum.text());

            } catch (IOException e) {
                e.printStackTrace();
            }

            //초기화
            addresslist = new ArrayList<>();
            juso = new ArrayList<>();
            num = new ArrayList<>();

            int cnt = 0;//주소 index

            //0, 2, 4... 만 저장
            for(Element element: adrs) {
                if((cnt % 2) == 0) {
                    juso.add(element.text());
                }

                cnt++;
            }

            for(Element element: adrNum) {
                num.add(element.text());
            }

            for (int i=0; i<juso.size(); i++){
                addresslist.add(juso.get(i) + "\n" + num.get(i));
                Log.d("num", num.get(i));
                Log.d("addresslist", addresslist.get(i));
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            AddressAdapter addressAdapter = new AddressAdapter(juso);
            recyclerView.setAdapter(addressAdapter);
        }
    }
}