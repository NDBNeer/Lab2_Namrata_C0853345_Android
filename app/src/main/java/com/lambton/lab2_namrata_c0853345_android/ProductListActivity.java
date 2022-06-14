package com.lambton.lab2_namrata_c0853345_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lambton.lab2_namrata_c0853345_android.adapter.ProductDetailAdapter;
import com.lambton.lab2_namrata_c0853345_android.adapter.ProductRecyclerViewAdapter;
import com.lambton.lab2_namrata_c0853345_android.model.Products;
import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {

    EditText search_product;
    RecyclerView product_list;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton add_prod;
    ArrayList<Products> prodlist;
    ProductRecyclerViewAdapter adapter;
    DbAdapter dbAdapter;
    Toolbar toolbar;
    Context mc;
    String searchText;
    Button btn_search_desc,btn_search_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);
        findId();
        toolbar.setTitle("Product List");
        setSupportActionBar(toolbar);
        mc=ProductListActivity.this;
        prodlist=new ArrayList<>();
        dbAdapter=new DbAdapter(mc);
        Cursor row = dbAdapter.getAllProducts();
        //row.moveToFirst();
        if (row.moveToFirst()){
            do {
                String column1 = row.getString(0);
                String column2 = row.getString(1);
                String column3 = row.getString(2);
                String column4 = row.getString(3);
                Products data = new Products(Integer.parseInt(column1), column2,column3,Double.parseDouble(column4));
                prodlist.add(data);
                Log.d("PRODLIST:", data.getProduct_name());

            } while(row.moveToNext());
        }
        row.close();


        layoutManager = new LinearLayoutManager(mc, LinearLayoutManager.VERTICAL, false);
        product_list.setLayoutManager(layoutManager);
        adapter = new ProductRecyclerViewAdapter(prodlist,mc);
        product_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void findId() {
        search_product=findViewById(R.id.search_product);
        toolbar = findViewById(R.id.toolbar);
        product_list=findViewById(R.id.product_list);
        add_prod=findViewById(R.id.add_prod);
        btn_search_desc=findViewById(R.id.btn_search_desc);
        btn_search_name=findViewById(R.id.btn_search_name);
        add_prod.setOnClickListener(this);
        btn_search_desc.setOnClickListener(this);
        btn_search_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_prod:
                startActivity(new Intent(ProductListActivity.this,AddProductActivity.class));
               break;
            case R.id.btn_search_desc:
                 searchText = search_product.getText().toString();
                Cursor cursor = null;
                cursor = dbAdapter.findProductByDescription(searchText);
                if (cursor != null && cursor.moveToFirst()){
                    do {
                        // Passing values
                        String column1 = cursor.getString(0);
                        String column2 = cursor.getString(1);
                        String column3 = cursor.getString(2);
                        String column4 = cursor.getString(3);
                    } while(cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.btn_search_name:
                 searchText = search_product.getText().toString();
                cursor = dbAdapter.findProductByName(searchText);
                if (cursor != null && cursor.moveToFirst()){
                    do {
                        // Passing values
                        String column1 = cursor.getString(0);
                        String column2 = cursor.getString(1);
                        String column3 = cursor.getString(2);
                        String column4 = cursor.getString(3);
                    } while(cursor.moveToNext());
                }
                cursor.close();
                break;
        }

    }
}
