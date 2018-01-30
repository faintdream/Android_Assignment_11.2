package com.akashdubey.autocompletedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;

import java.util.ArrayList;

import db.DBHelper;
import utils.Constants;


public class MainActivity extends AppCompatActivity {
    static DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DBHelper(getApplicationContext());
        AutoCompleteTextView products=(AutoCompleteTextView)findViewById(R.id.productTV);
        ArrayList<String> tmpProductList = new ArrayList<String>();
        InsertData();
        GetData();
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            tmpProductList.add(cursor.getString(cursor.getColumnIndex(Constants.PRODUCT_NAME)));
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tmpProductList);
        products.setThreshold(1);
        products.setAdapter(adapter);
    }

    void GetData(){
        if(dbHelper == null){
            dbHelper.OpenConnection();
        }
//        sqLiteDaquery(Constants.TABLE_NAME,new String[]{Constants.PRODUCT_NAME},null,null,null,null,null);
          cursor=dbHelper.sqLiteDatabase.query(Constants.TABLE_NAME,new String[]{Constants.PRODUCT_NAME},null,null,null,null,null);
    }

    void InsertData(){
        dbHelper.OpenConnection();
        String[] productList={"Honor6x","LG V30+","MotoG g5s plus",
                "Honor 8 pro" , "Xiomi A1", "Xiomi Note 4",
                "Yu Yureka", "Samsung s8 edge", "Samsung J7 prime",
                "iphone5s", "IPhone SE","Iphone 6",
                "iphone 7","iphone8","iphone X"};
        ContentValues value=new ContentValues();
        for(String item:productList) {
            value.put(Constants.PRODUCT_NAME, item);
            dbHelper.sqLiteDatabase.insert(Constants.TABLE_NAME,null,value);
        }

    }



}
