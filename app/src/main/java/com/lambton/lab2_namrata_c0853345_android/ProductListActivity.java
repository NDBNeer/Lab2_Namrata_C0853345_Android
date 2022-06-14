package com.lambton.lab2_namrata_c0853345_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lambton.lab2_namrata_c0853345_android.adapter.ProductDetailAdapter;
import com.lambton.lab2_namrata_c0853345_android.model.Products;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {

    EditText search_product;
    RecyclerView product_list;
    FloatingActionButton add_prod;
    ArrayList<Products> prodlist;
    ProductDetailAdapter adapter;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);
        findId();
        mc=ProductListActivity.this;
        prodlist=new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        product_list.setLayoutManager(mLayoutManager);
        adapter = new ProductDetailAdapter(mc,prodlist);
        product_list.setAdapter(adapter);
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
