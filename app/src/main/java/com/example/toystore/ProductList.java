package com.example.toystore;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.Adapters.ProductAdapter;
import com.example.toystore.Models.Product;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    ArrayList<Product> proList;
    DBHelper helper;

    Button addPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        addPro = (Button) findViewById(R.id.addPro);
        helper = new DBHelper(ProductList.this);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_product);

        proList = new ArrayList<>();

        proList = helper.getproduct(ProductList.this);


        ProductAdapter adapter = new ProductAdapter(proList);

        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));

        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}