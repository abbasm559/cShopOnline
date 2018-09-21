package com.example.abbas.cshoponline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ProductDetailsActivity extends AppCompatActivity {
    Context context = this;
    DatabaseManager databaseManager;
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
       // Random r = new Random();
        //int i1 = r.nextInt(600 - 400) + 400;
        // String ordNumber= String.valueOf(i1);
        String title = titleTextView.getText().toString();
        String quantity = quantityTv.getText().toString();
        String price = priceTextView.getText().toString();

        databaseManager = new DatabaseManager(context);
        sqLiteDatabase = databaseManager.getWritableDatabase();
        databaseManager.insertData(title,quantity,price,sqLiteDatabase);
        Toast.makeText(getApplicationContext(), "Data inserted!!", Toast.LENGTH_SHORT).show();
        databaseManager.close();


    }
    public void showOnCart(){
        Cursor cursor = databaseManager.getCartData();
        if (cursor.moveToFirst()){
            StringBuffer stringBuffer = new StringBuffer();
        if (cursor.getCount()!=0){
            do {

               stringBuffer.append("Title: " +cursor.getString(1)+"\n");
               stringBuffer.append("Quantity: "+cursor.getString(2)+"\n");
               stringBuffer.append("Price: "+cursor.getString(3)+"\n\n");

            }while (cursor.moveToNext());
        }

        Intent intent = new Intent(ProductDetailsActivity.this,CartDetailsActivity.class);
        intent.putExtra("msg",stringBuffer.toString());
        startActivity(intent);
           // getCartData("Total Cart element",stringBuffer.toString());

        }else {return;}
    }
    /*public void getCartData(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }*/

    public void viewCartItem(View view) {

        showOnCart();

    }




    public void showCartAmmount(){
        Cursor cursor = databaseManager.getCartData();
        StringBuffer stringBuffer = new StringBuffer();

        if (cursor.getCount()==0){
            return;
        }else {
            while (cursor.moveToNext()){
                for (int i=0;i<cursor.getCount();i++){
                    stringBuffer.append(cursor.getString(2));
                   int totalCount= Integer.parseInt(stringBuffer.toString());
                    totalCount+=totalCount;
                    cartQtt.setText(String.valueOf(totalCount));
                }

            }
        }


    }



















    public class DatabaseManager extends SQLiteOpenHelper {
        static final String DATABASE_NAME = "cartDatabase";
        static final int DATABASE_VERSION = 1;
        static final String TABLE_NAME= "productTable";
        static final String ID = "_id";
        static final String TITLE ="title";
        static final String QUANTITY ="quantity";
        static final String PRICE ="price";
        static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+" (" +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TITLE+" TEXT, "+QUANTITY+" TEXT, "+PRICE+" TEXT );";


        public DatabaseManager(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(getApplicationContext(), "onCreate is called!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            Toast.makeText(getApplicationContext(), "Table droped!", Toast.LENGTH_SHORT).show();
            onCreate(db);

        }

        public void insertData(String title, String quantity,String price, SQLiteDatabase database){
            ContentValues contentValues = new ContentValues();
            contentValues.put(TITLE,title);
            contentValues.put(QUANTITY,quantity);
            // contentValues.put(ProductCredentials.orderNumber,orderNumber);
            contentValues.put(PRICE,price);

            database.insert(TABLE_NAME,null,contentValues);

        }
        public Cursor getCartData(){

            sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            return cursor;

        }

    }
}
