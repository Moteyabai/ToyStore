package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.toystore.Adapters.ProductAdapter;
import com.example.toystore.Models.Product;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    ArrayList<Product> proList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_product);

        proList = new ArrayList<>();
        proList.add(new Product(1, "Toy", 30000, "something"));

        ProductAdapter adapter = new ProductAdapter(proList);

        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}