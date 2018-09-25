package com.example.abbas.cshoponline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abbas.cshoponline.models.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlaceOrderActivity extends AppCompatActivity {
    EditText nameEt,phoneEt,addressEt;
    RadioButton btnBkash,btnCoD;
    TextView totalPriceTv,shiftingCostTv,totalCostTv;

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
        totalPriceTv = findViewById(R.id.totalPriceTV);
        shiftingCostTv = findViewById(R.id.shiftingCostTv);
        totalCostTv = findViewById(R.id.totalCostTv);

        reference = database.getReference("orders");


    }

    public void sendInformation(View view) {
        addOrder();
    }

    public void addOrder(){
        String name = nameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();
        String totalCost = totalCostTv.getText().toString();
        if (name.isEmpty()){
            nameEt.setError("Input name");
        }else if (phone.isEmpty()){
            phoneEt.setError("Input phone number");
        }else if (address.isEmpty()){
            addressEt.setError("Address to receive product");
        }else {

            String orderNumber = reference.push().getKey();
            Order order = new Order(name,phone,address,orderNumber,totalCost);
            assert orderNumber != null;
            reference.child(orderNumber).setValue(order);

            //to see the data go to https://cshoponline-71fc1.firebaseio.com/

            /*nameEt.setText("");
            phoneEt.setText("");
            addressEt.setText("");*/

            Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();

        }
    }
}
