package com.lambton.lab2_namrata_c0853345_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.lambton.lab2_namrata_c0853345_android.R;
import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddToDb;
    EditText etProductName, etProductDescription, etProductPrice;
    DbAdapter dbAdapter = new DbAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        findId();

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
        }
    }
}