package com.example.abbas.cshoponline;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartDetailsActivity extends AppCompatActivity {
    DatabaseManager databaseManager;
    ListView  cartList;
    ArrayList<String> listCart;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);

        cartList = findViewById(R.id.cartList);
        listCart = new ArrayList<>();
        adapter = new ArrayAdapter<>(CartDetailsActivity.this,android.R.layout.simple_list_item_1,listCart);
        cartList.setAdapter(adapter);

        getAllCartData();


}

    private void getAllCartData() {
        databaseManager = new DatabaseManager(this);
        Cursor cursor = databaseManager.getAllCartItem();
        if (!cursor.moveToNext()){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else {
do {
    listCart.add(cursor.getString(1)+"  Qtt: "+cursor.getString(2)+"  Price: "+cursor.getString(3));

}while (cursor.moveToNext());
    }
}}
