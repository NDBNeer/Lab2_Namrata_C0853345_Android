package com.lambton.lab2_namrata_c0853345_android;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lambton.lab2_namrata_c0853345_android.R;
import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

public class EditProduct extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    DbAdapter dbAdapter = new DbAdapter(this);
    String prod_id,prod_name,prod_desc,prod_price;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        findId();
        toolbar.setTitle("Edit Product");
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        prod_id = i.getStringExtra("prod_id");
        prod_name=i.getStringExtra("prod_name");
        prod_desc = i.getStringExtra("prod_desc");
        prod_price= i.getStringExtra("prod_price");
        etProductName.setText(prod_name);
        etProductDescription.setText(prod_desc);
        etProductPrice.setText(prod_price);
    }
    private void findId(){
        etProductName = findViewById(R.id.product_name);
        etProductDescription = findViewById(R.id.product_description);
        etProductPrice = findViewById(R.id.product_price);
        btnAddToDb = findViewById(R.id.btn_add);
        btnAddToDb.setText("Edit Product");
        btnAddToDb.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add){
            dbAdapter.updateProduct(Integer.parseInt(prod_id),etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString()));
        }
    }
}
