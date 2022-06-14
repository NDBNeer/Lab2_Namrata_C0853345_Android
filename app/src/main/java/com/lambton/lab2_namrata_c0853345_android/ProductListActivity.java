package com.lambton.lab2_namrata_c0853345_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lambton.lab2_namrata_c0853345_android.adapter.ProductDetailAdapter;
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
    ProductDetailAdapter adapter;
    DbAdapter dbAdapter;
    Toolbar toolbar;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);
        findId();
        toolbar.setTitle("Product List");
        setSupportActionBar(toolbar);
        layoutManager = new LinearLayoutManager(mc, LinearLayoutManager.VERTICAL, false);
        product_list.setLayoutManager(layoutManager);
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
        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mc);
        product_list.setLayoutManager(mLayoutManager);*/
         adapter = new ProductDetailAdapter(this, R.layout.product_list_detailview, prodlist);

        //adapter = new ProductDetailAdapter(mc,prodlist);
        product_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void findId() {
        search_product=findViewById(R.id.search_product);

        product_list=findViewById(R.id.product_list);
        add_prod=findViewById(R.id.add_prod);
        add_prod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(ProductListActivity.this,AddProductActivity.class));
    }
}
