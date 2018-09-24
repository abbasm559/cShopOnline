package com.example.abbas.cshoponline;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseManager databaseManager= new DatabaseManager(this);
    Spinner spinner;
    TextView cartTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartTV = findViewById(R.id.cartTextView);


        showCartText();


        ListView listView;
        ProductAdapter mAdapter;
        spinner = findViewById(R.id.spinnerId);
        List<String>categories = new ArrayList<>();
       // categories.add("Select Category");
        categories.add("All Products");
        categories.add("Electronics");
        categories.add("Clothes");
        categories.add("Cosmetics");
        categories.add("Sports");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setPrompt("Select Category");
        spinner.setAlpha(1);


        // --------- Properties of every list item--------------

        //----------titles----------------------
        final String pakhi3PcsTitle="Pakhi 3pcs";
        final String makeUpBoxTitle="Make up box";
        final String chakriThakbeNaTitle="Chakri thakbe na T-shirt";
        final String cardMobileTitle="Card mobile";
        final String smartWatchTitle="Smart watch DZ-08";
        final String tendaRouterTitle="Tenda router";
        final String waterHeatheTitle="Water heater";
        final String kidswearTitle="Kids wear kit";
        final String smartPenTitle="Smart pen";
        //------------descriptions---------------------
        final String pakhi3PcsDes="\n" +
                "\n" +
                "    অরিজিনাল প্রোডাক্ট\n" +
                "\n" +
                "    আনস্টিচড থ্রি পিস\n" +
                "\n" +
                "    সালোয়ার: কটন\n" +
                "\n" +
                "    কামিজ ফেব্রিক- ফুল জর্জেট এমব্রয়ডারি\n" +
                "\n" +
                "    ওড়না: শিফন\n" +
                "\n" +
                "    হাই কোয়ালিটি ফেব্রিক\n" +
                "\n" +
                "    আকর্ষণীয় ডিজাইন\n" +
                "\n" +
                "    ফ্যাশনেবল ও আরামদায়ক\n"; final String price3Pcs= "3450";

        final String makeUpBoxDes="Product details of Cosmetics Organizer Box - Transparent\n" +
                "\n" +
                "    Type: Storage Boxes\n" +
                "    Material: Plastic\n" +
                "    Style: Modern\n" +
                "    Color: Transparent\n" +
                "    Environmentally friendly, Stocked\n" +
                "    Ideal for organizing and storing your makeup"; final String makeUpBoxPri= "1990";

        final String chakriTNDes="\n" +
                "    Fabrics: Cotton\n" +
                "    Fabrication: 180 GSM\n" +
                "    Attractive Design\n" +
                "    Color: As Given Picture\n" +
                "    Computer Marvel Rubber Print\n" +
                "    N.B: Please Check The Size Chart And Select Your Size Before Placing Order.\n" +
                "    Disclaimer: The Actual Color Of The Physical Product May Slightly Vary Due To The Deviation Of Lighting Sources, Photography Or Your Device Display Settings."; final String tShirtPrice ="999";

        final String cardMobileDes="\n" +
                "\n" +
                "ব্র্যান্ড: AEKU\n" +
                "\n" +
                "মডেল: C6\n" +
                "\n" +
                "র\u200D্যাম: <128 মেগাবাইট\n" +
                "\n" +
                "টকটাইম: ৪ ঘণ্টা (প্রায়)\n" +
                "\n" +
                "স্ট্যান্ডবাই টাইম: ২ দিন (প্রায়)\n" +
                "\n" +
                "ব্যাটারি: 320mAh\n" +
                "\n" +
                "ন্যানো সিম\n" +
                "\n" +
                "সিঙ্গেল সিম\n" +
                "\n" +
                "কালার: র\u200C্যান্ডম\n"; final String carMobPrice="2400";

        final String smartWatchDes="    Display: 1.54'' IPSCapacitive Touchscreen\n" +
                "    Resolutions: 240 x 240\n" +
                "    Compatible OS: Android\n" +
                "    RAM: 64MB, ROM: 128MB\n" +
                "    Single SIM\n" +
                "    Camera: VGA"; final String smartWatchPri= "1400";

        final String tendaRouterDes="Wireless Speed\t2.4GHZ:300Mbps II\n" +
                "5GHZ:867Mbps II\n" +
                "Button\t1*WPS/RESET button\n" +
                "Antenna Type\t5 External Antennas\n" +
                "Wireless Standards\t1 10/100M auto-negotiation WAN port II\n" +
                "3 10/100M auto-negotiation LAN ports II\n" +
                "Frequency\tWorks over 2.4GHz and 5GHz II\n" +
                "Basic Functions Wireless Switch II\n" +
                "SSID Broadcast Switch II\n" +
                "Work Frequency: 2.4GHz,5GHz II\n" +
                "Channel Of 2.4GHz:1-13 II\n" +
                "Channel Of 5GHz: 149、153、157、161、165 II"; final String routerPrice = "1950";

        final String waterHeaterDes="\n" +
                "\n" +
                "    *Brand: Hyundai\n" +
                "\n" +
                "    *Model: REK-105\n" +
                "\n" +
                "    *Power: 1550 W\n" +
                "\n" +
                "    *Voltage: 220 V\n" +
                "\n" +
                "    *Capacity: 1.8 LITER\n" +
                "\n" +
                "    *Size: 190x190x230 mm\n" +
                "\n" +
                "    *3 Leyar safety.\n" +
                "\n" +
                "    *Auto Switch Off Function after Boield.\n" +
                "\n" +
                "    *Mirror polished Stainless Steel.\n"; final String wHeaterPri ="1150";

        final String kidsWearDes="\n" +
                "\n" +
                "    Product type: Baby pack 7 pcs set.\n" +
                "    Brand: Cussons.\n" +
                "    Biodegradable and dermatologically tested.\n" +
                "    Good quality for kids.                                                                                           \n" +
                "    Made in Indonesia.\n" +
                "    Color: As same as picture.\n" +
                "\n"; final String kidswearPri = "5580";

        final String smartPenDes="\n" +
                "    Touch pen for smartphone and Tablets\n" +
                "    Stylus Pen for Capacitive Touch screens\n" +
                "    Pen clip for easy carry and storage\n" +
                "    Super soft and flexible 7mm stylus tip design is replaceable\n" +
                "    Highly sensitive and durable pen tip provides precision control\n" +
                "    500,000 tap times for pen tip - SGS certified\n"; final String smartPenPri= "2200";


        listView =findViewById(R.id.productListId);
        final ArrayList<Product> productList = new ArrayList<>();


                        productList.add(new Product(R.drawable.three_pcs, pakhi3PcsTitle ,pakhi3PcsDes, price3Pcs));
                        productList.add(new Product(R.drawable.make_up, makeUpBoxTitle , makeUpBoxDes ,makeUpBoxPri));
                        productList.add(new Product(R.drawable.t_shirt, chakriThakbeNaTitle ,chakriTNDes, tShirtPrice));
                        productList.add(new Product(R.drawable.card_mobile, cardMobileTitle ,cardMobileDes, carMobPrice));
                        productList.add(new Product(R.drawable.sm_watch, smartWatchTitle ,smartWatchDes, smartWatchPri));
                        productList.add(new Product(R.drawable.router, tendaRouterTitle ,tendaRouterDes ,routerPrice));
                        productList.add(new Product(R.drawable.water_heater, waterHeatheTitle ,waterHeaterDes ,wHeaterPri));
                        productList.add(new Product(R.drawable.kids_wear, kidswearTitle ,kidsWearDes ,kidswearPri));
                        productList.add(new Product(R.drawable.smart_pen, smartPenTitle ,smartPenDes ,smartPenPri));
                        

        mAdapter = new ProductAdapter(this,productList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Product product = productList.get(i);

                Intent intent = new Intent(MainActivity.this,ProductDetailsActivity.class);

                intent.putExtra(ProductCredentials.imageKye,product.getProductImage());
                intent.putExtra(ProductCredentials.titleKey,product.getTitle());
                intent.putExtra(ProductCredentials.descriptionKye,product.getDescription());
                intent.putExtra(ProductCredentials.priceKey,product.getPrice());

                startActivity(intent);
            }
        });

    }
    public void showCartText(){
        Cursor cursor = databaseManager.getCartData();
        // StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToFirst()){
            String result = String.valueOf(cursor.getInt(cursor.getColumnIndex("totalQtt")));
            cartTV.setText(result);
        }
    }
    public void cartIntent(View view){
        if (cartTV.getText().toString().equals("0")){
            Toast.makeText(this, "No item added to cart yet!", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this,CartDetailsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCartText();
    }
}
