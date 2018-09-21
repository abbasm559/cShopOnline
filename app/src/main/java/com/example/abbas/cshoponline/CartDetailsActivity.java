package com.example.abbas.cshoponline;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CartDetailsActivity extends AppCompatActivity {
    ProductDetailsActivity.DatabaseManager databaseManager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        textView = findViewById(R.id.cartDetailsTv);

        Intent intent = getIntent();
        String message = intent.getStringExtra("msg");
        textView.setText(message);

        /*Cursor cursor = databaseManager.getCartData();
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
            if (cursor.getCount()!=0){
                do {

                    stringBuffer.append("Title: " +cursor.getString(1)+"\n");
                    stringBuffer.append("Quantity: "+cursor.getString(2)+"\n");
                    stringBuffer.append("Price: "+cursor.getString(3)+"\n\n");

                }while (cursor.moveToNext());
            }

            getCartData("Total Cart element",stringBuffer.toString());

        }else {return;}

    }
    public void getCartData(String title,String message){

        textView.setText(message);

       *//* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();*//*
    }*/
}}
