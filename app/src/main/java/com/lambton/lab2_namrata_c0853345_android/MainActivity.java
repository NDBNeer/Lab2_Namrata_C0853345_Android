package com.lambton.lab2_namrata_c0853345_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

public class MainActivity extends AppCompatActivity {

    TextView prod_name,prod_desc,prod_price;
    Button view_more;
    DbAdapter dbAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        context=MainActivity.this;
        dbAdapter=new DbAdapter(context);
        insertdatadb();
        Cursor row = dbAdapter.getFirstProduct();
        if (row.moveToFirst()){
            do {
                // Passing values
                prod_name.setText(row.getString(1));
                prod_desc.setText(row.getString(2));
                prod_price.setText(row.getString(3));
            } while(row.moveToNext());
        }
        row.close();
        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void insertdatadb() {
        dbAdapter.addProduct("Apple","Apple bulk",15.00);
        dbAdapter.addProduct("Milk","Homo Milk",01.00);
        dbAdapter.addProduct("Banana","Fruit Banana bulk",10.00);
        dbAdapter.addProduct("Chocolate","Cadbury dairy milk",60.00);
        dbAdapter.addProduct("Chocolate Muffin","Chocolate Muffin",70.00);
        dbAdapter.addProduct("Kitkat","kitkat chocolate mini pack",90.00);
        dbAdapter.addProduct("Orange Juice","Natural orange juice",20.00);
        dbAdapter.addProduct("Apple Juice","Natural apple juice",15.00);
        dbAdapter.addProduct("Coke","Original coke",70.20);
    }

    private void findID() {
        prod_name=findViewById(R.id.prod_name);
        prod_desc=findViewById(R.id.prod_desc);
        prod_price=findViewById(R.id.prod_price);
        view_more=findViewById(R.id.view_more);
    }
}