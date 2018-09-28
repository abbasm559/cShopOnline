package com.example.abbas.cshoponline.activity_classes;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.abbas.cshoponline.DatabaseManager;
import com.example.abbas.cshoponline.R;

import java.util.ArrayList;

public class CartDetailsActivity extends AppCompatActivity {
    DatabaseManager databaseManager;
    ListView cartList;
    ArrayList<String> listCart;
    ArrayAdapter<String> adapter;
    TextView totalPriceTv;
    ImageButton homeBtn;
    Cursor cursor;
    ArrayList<String> ids;
    ArrayList<String>titles;
    ArrayList<String> prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        homeBtn = findViewById(R.id.homeBtn);
        cartList = findViewById(R.id.cartList);
        totalPriceTv = findViewById(R.id.totalPriceTV);
        listCart = new ArrayList<>();
        adapter = new ArrayAdapter<>(CartDetailsActivity.this,R.layout.cart_list_item,listCart);
        cartList.setAdapter(adapter);

        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CartDetailsActivity.this);
                builder.setTitle("Chose an action");
                builder.setMessage("You can update or delete this item.");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = ids.get(position);
                      int result = databaseManager.deleteCartItem(id);
                        if (result>0){
                            Toast.makeText(CartDetailsActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }
                    }
                }).setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText editText = new EditText(CartDetailsActivity.this);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                        AlertDialog.Builder builderUpdate = new AlertDialog.Builder(CartDetailsActivity.this);
                        builderUpdate.setView(editText);
                        builderUpdate.setTitle("Input Quantity");
                        builderUpdate.setCancelable(true);
                        builderUpdate.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = ids.get(position);
                                String title = titles.get(position);
                                String price = prices.get(position);
                                String quantity= editText.getText().toString().trim();
                                int quantityInInt = 1;
                                if (quantity.isEmpty()||quantity.startsWith("0")){
                                    Toast.makeText(CartDetailsActivity.this, "Input a valid number", Toast.LENGTH_LONG).show();
                                }else{
                                    quantityInInt = Integer.parseInt(quantity);
                                    boolean updateData = databaseManager.updateCart(id,title,quantityInInt,Integer.parseInt(price));
                                    if (updateData){
                                        Toast.makeText(CartDetailsActivity.this, "Quantity updated!", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                }
                            }
                        }).show();
                    }
                }).show();
            }
        });
        getAllCartData();
        getTotalPrice();
}
private void getAllCartData() {
        databaseManager = new DatabaseManager(this);
        ids = new ArrayList<>();
        titles = new ArrayList<>();
        prices = new ArrayList<>();
        cursor = databaseManager.getAllCartItem();
        if (!cursor.moveToNext()){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else if (cursor.moveToFirst()){
do {
    ids.add(cursor.getString(0));
    titles.add(cursor.getString(1));
    prices.add(cursor.getString(3));
    listCart.add(cursor.getString(1)+"  Qtt: "+cursor.getString(2)+"  Price: "+cursor.getString(3)+"/-");
}while (cursor.moveToNext());
    }
}
private void getTotalPrice(){
        databaseManager = new DatabaseManager(this);
        cursor = databaseManager.getTotalPrice();
        if (cursor.moveToFirst()){
            String result = String.valueOf(cursor.getInt(cursor.getColumnIndex("TotalPrice")));
            totalPriceTv.setText(result);
        }
}

    @Override
    public void onBackPressed() {
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(50);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(7);
        mAnimation.setRepeatMode(Animation.REVERSE);
        homeBtn.startAnimation(mAnimation);
    }

    public void placeOrder(View view) {
        Intent intent = new Intent(CartDetailsActivity.this,PlaceOrderActivity.class);
        intent.putExtra("totalPrice",totalPriceTv.getText().toString());
        startActivity(intent);
    }

    public void homeIntent(View view) {
        Intent intent = new Intent(CartDetailsActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
