package com.example.abbas.cshoponline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseManager extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    static final String DATABASE_NAME = "cartDatabase";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME= "productTable";
    static final String ID = "_id";
    static final String TITLE ="title";
    static final String QUANTITY ="quantity";
    static final String PRICE ="price";
    static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+" (" +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TITLE+" TEXT, "+QUANTITY+" INTEGER, "+PRICE+" INTEGER );";


    public DatabaseManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public void insertData(String title, int quantity,int price, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title);
        contentValues.put(QUANTITY,quantity);
        // contentValues.put(ProductCredentials.orderNumber,orderNumber);
        contentValues.put(PRICE,price);

        database.insert(TABLE_NAME,null,contentValues);

    }
    public Cursor getCartData(){

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM ("+QUANTITY+") AS totalQtt FROM "+TABLE_NAME,null);
        return cursor;

    }
    public Cursor getAllCartItem(){
        sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
    public boolean deleteCartItem(int id){
        sqLiteDatabase = this.getWritableDatabase();
        try {
        int deleteValue= sqLiteDatabase.delete(TABLE_NAME,ID+" =?", new String[]{String.valueOf(id)});
        if (deleteValue>0){
            return true;
        }
         }catch (Exception e){

        }
        return false;
    }
    public Cursor getTotalPrice(){
        sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM("+PRICE+") AS TotalPrice FROM "+TABLE_NAME,null);
        return cursor;
    }

}
