package com.example.abbas.cshoponline.activity_classes;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abbas.cshoponline.DatabaseManager;
import com.example.abbas.cshoponline.R;
import com.example.abbas.cshoponline.models.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {
    EditText nameEt,phoneEt,addressEt;
    RadioButton btnBkash,btnCoD;
    TextView totalPriceTv,shiftingCostTv,totalCostTv,thankTxt;
    ListView listView;
    List<String>orderList;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        addressEt= findViewById(R.id.addressEt);
        btnBkash = findViewById(R.id.radioBkash);
        btnCoD = findViewById(R.id.radioCoD);
        totalPriceTv = findViewById(R.id.totalPriceTextView);
        shiftingCostTv = findViewById(R.id.shiftingCostTv);
        totalCostTv = findViewById(R.id.totalCostTv);
        thankTxt = findViewById(R.id.thankText);
        //listView = findViewById(R.id.finalListOfOrder);

        String totalPrice = getIntent().getStringExtra("totalPrice");
        totalPriceTv.setText(totalPrice+"/-");
        int totalPriceInt = Integer.parseInt(totalPrice);
        int totalCost = totalPriceInt+40;
        String totalCostString = String.valueOf(totalCost)+"/-";
        totalCostTv.setText(totalCostString);

        reference = database.getReference("orders");


        DatabaseManager databaseManager=new DatabaseManager(PlaceOrderActivity.this);
        Cursor cursor = databaseManager.getAllCartItem();
        orderList= new ArrayList<>();
        /*ArrayAdapter<String> orderListAdepter = new ArrayAdapter<>(PlaceOrderActivity.this,
                android.R.layout.simple_list_item_1,orderList);
        listView.setAdapter(orderListAdepter);*/
        if (cursor.moveToFirst()) {
            do {
                orderList.add(cursor.getString(1) + "  Qty: "
                        + cursor.getString(2) + "  Price: " + cursor.getString(3));
            }while (cursor.moveToNext());

        }
    }

    public void sendInformation(View view) {
         addOrder();
    }

    public void addOrder(){
        String name = nameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();
        String totalCost = totalCostTv.getText().toString();
        final String orderNumber = reference.push().getKey();
        if (name.isEmpty()){
            nameEt.setError("Input name");
        }else if (phone.isEmpty()){
            phoneEt.setError("Input phone number");
        }else if (address.isEmpty()){
            addressEt.setError("Address to receive product");
        }else {
            Order order = new Order(name,phone,address,orderNumber,totalCost);
            reference.child(orderNumber).setValue(order);
            reference.child(orderNumber+"Products").setValue(orderList).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    nameEt.setText("");
                    phoneEt.setText("");
                    addressEt.setText("");
                    thankTxt.setText(getString(R.string.thank_text)+orderNumber);
                    thankTxt.setVisibility(View.VISIBLE);
                    Toast.makeText(PlaceOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PlaceOrderActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });



            //to see the data go to https://cshoponline-164f0.firebaseio.com/



        }
    }

    public void logInIntent(View view) {
        Intent loginIntent=new Intent(PlaceOrderActivity.this,LoginActivity.class);
        startActivity(loginIntent);

    }
}
