package com.example.abbas.cshoponline.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abbas.cshoponline.R;
import com.example.abbas.cshoponline.models.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> orderList;
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("orders");


    public OrderAdapter(Activity context, List<Order> orderList) {
        super(context, R.layout.owner_order_list,orderList);
        this.context=context;
        this.orderList = orderList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listForOrder = inflater.inflate(R.layout.owner_order_list,null,true);

        TextView textViewBuyer= listForOrder.findViewById(R.id.buyerText);
        TextView textViewOrder= listForOrder.findViewById(R.id.orderText);

        Order order = orderList.get(position);
        textViewBuyer.setText(order.getBuyerName()+"  "+order.getPhoneNumber());
        textViewOrder.setText(order.getAddress()+"  "+order.getTotalCost());

        return listForOrder;
    }
}
