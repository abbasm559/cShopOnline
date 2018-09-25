package com.example.abbas.cshoponline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    private List<Product> productList = new ArrayList<>();

    public ProductAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Product> list) {
        super(context, 0 , list);
        mContext = context;
        productList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Product currentProduct = productList.get(position);

        ImageView image = listItem.findViewById(R.id.idProductImage);
        image.setImageResource(currentProduct.getProductImage());

        TextView name = listItem.findViewById(R.id.idTitleTv);
        name.setText(currentProduct.getTitle());

        TextView release = listItem.findViewById(R.id.icPriceTv);
        release.setText(currentProduct.getPrice()+"/-");

        return listItem;
    }
}
