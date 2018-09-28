package com.example.abbas.cshoponline.activity_classes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.abbas.cshoponline.R;
import com.example.abbas.cshoponline.adapters.OrderAdapter;
import com.example.abbas.cshoponline.models.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrderShowActivity extends AppCompatActivity {
    ListView listViewOfOrder;
    DatabaseReference reference;
    List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_order_show);

        reference = FirebaseDatabase.getInstance().getReference("orders");
        orderList = new ArrayList<>();

        listViewOfOrder = findViewById(R.id.orderListAtOwner);

    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot orderSnapshot: dataSnapshot.getChildren()){
                    orderList.clear();
                    Order order = orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }
                OrderAdapter adapter = new OrderAdapter(OwnerOrderShowActivity.this,orderList);
                listViewOfOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
