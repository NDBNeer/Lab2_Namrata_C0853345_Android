package com.lambton.lab2_namrata_c0853345_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lambton.lab2_namrata_c0853345_android.AddProductActivity;
import com.lambton.lab2_namrata_c0853345_android.EditProduct;
import com.lambton.lab2_namrata_c0853345_android.ProductListActivity;
import com.lambton.lab2_namrata_c0853345_android.R;
import com.lambton.lab2_namrata_c0853345_android.model.Products;
import com.lambton.lab2_namrata_c0853345_android.sqlite.DbAdapter;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<Products> courseDataArrayList;
    private Context mcontext;
    DbAdapter dbAdapter;

    // creating a constructor class.
    public ProductRecyclerViewAdapter(ArrayList<Products> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_detailview, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Products recyclerData = courseDataArrayList.get(position);
        dbAdapter=new DbAdapter(mcontext);
        holder.prod_name.setText(recyclerData.getProduct_name());
        holder.prod_desc.setText(recyclerData.getProduct_description());
        holder.prod_price.setText(String.valueOf(recyclerData.getProduct_price()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mcontext, EditProduct.class);
                i.putExtra("prod_id",recyclerData.getProduct_id());
                i.putExtra("prod_name",recyclerData.getProduct_name());
                i.putExtra("prod_desc",recyclerData.getProduct_description());
                i.putExtra("prod_price",recyclerData.getProduct_price());
                mcontext.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter.deleteProduct(recyclerData.getProduct_id());
                //notifyDataSetChanged();
                mcontext.startActivity(new Intent(mcontext, ProductListActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns
        // the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // creating a variable for our text view.
        private TextView prod_name,prod_desc,prod_price;
        private Button edit,delete;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_desc = itemView.findViewById(R.id.prod_desc);
            prod_price = itemView.findViewById(R.id.prod_price);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}