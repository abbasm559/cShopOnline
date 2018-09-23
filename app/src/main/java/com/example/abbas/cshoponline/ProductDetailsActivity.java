package com.example.abbas.cshoponline;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProductDetailsActivity extends AppCompatActivity {
    Context context = this;
    DatabaseManager databaseManager = new DatabaseManager(this);
    SQLiteDatabase sqLiteDatabase;


    TextView titleTextView,descriptionTextView,priceTextView,quantityTv,cartQtt;
    ImageView imageView;

    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        titleTextView = findViewById(R.id.detailedTitle);
        descriptionTextView = findViewById(R.id.productDesctiption);
        priceTextView = findViewById(R.id.idDetailPrice);
        quantityTv= findViewById(R.id.quantityTv);
        imageView = findViewById(R.id.detailedImage);
        cartQtt = findViewById(R.id.cartQtt);
        Intent intent = getIntent();
        int imageID =getIntent().getIntExtra(ProductCredentials.imageKye,0);

        String title = intent.getStringExtra(ProductCredentials.titleKey);
        String description = intent.getStringExtra(ProductCredentials.descriptionKye);
        price = intent.getStringExtra(ProductCredentials.priceKey);
        imageView.setImageResource(imageID);
        descriptionTextView.setText(description);
        titleTextView.setText(title);
        priceTextView.setText(price);
        quantityTv.setText("1");
        showData();
    }

    public void add(View view) {
        int currentQtt = Integer.parseInt(quantityTv.getText().toString());
        currentQtt = currentQtt+1;
        quantityTv.setText(String.valueOf(currentQtt));
        int currentPrice =Integer.parseInt(price);
        int addedPrice = currentPrice * currentQtt;
        priceTextView.setText(String.valueOf(addedPrice));
    }

    public void sub(View view) {
        int currentQtt = Integer.parseInt(quantityTv.getText().toString());
        if (currentQtt>0) {
            currentQtt = currentQtt - 1;
        }
        quantityTv.setText(String.valueOf(currentQtt));
        int currentPrice = Integer.parseInt(price);
        int subedPrice = currentPrice*currentQtt;
        priceTextView.setText(String.valueOf(subedPrice));
    }



    public void addToCart(View view) {
        String title = titleTextView.getText().toString();
        int quantity = Integer.parseInt(quantityTv.getText().toString());
        String price = priceTextView.getText().toString();

        databaseManager = new DatabaseManager(context);
        sqLiteDatabase = databaseManager.getWritableDatabase();
        databaseManager.insertData(title,quantity,price,sqLiteDatabase);
        Toast.makeText(getApplicationContext(), "Product added to cart!", Toast.LENGTH_SHORT).show();
        databaseManager.close();
        showData();
    }
    public  void showData(){
        Cursor cursor = databaseManager.getCartData();
       // StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToFirst()){
                String result = String.valueOf(cursor.getInt(cursor.getColumnIndex("totalQtt")));
                cartQtt.setText(result);
        }

    }

    public void cartIntent(View view) {
        Intent intent = new Intent(ProductDetailsActivity.this,CartDetailsActivity.class);
        startActivity(intent);
    }

}
