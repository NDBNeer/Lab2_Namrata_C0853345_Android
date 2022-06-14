package com.lambton.lab2_namrata_c0853345_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lambton.lab2_namrata_c0853345_android.R;
import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    DbAdapter dbAdapter = new DbAdapter(this);
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        findId();
        toolbar.setTitle("Add Product");
        setSupportActionBar(toolbar);

    }
    private void findId(){
        etProductName = findViewById(R.id.product_name);
        etProductDescription = findViewById(R.id.product_description);
        etProductPrice = findViewById(R.id.product_price);
        btnAddToDb = findViewById(R.id.btn_add);
        btnAddToDb.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_add){
            dbAdapter.addProduct(etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString()));
            Toast.makeText(AddProductActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddProductActivity.this, ProductListActivity.class));
        }
    }
}