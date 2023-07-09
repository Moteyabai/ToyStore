package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.toystore.Adapters.ProductAdapter;
import com.example.toystore.Models.Product;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    Button logout, user, product;

    Button logout;
    ArrayList<Product> proList;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        logout = findViewById(R.id.logout);

        user = findViewById(R.id.btnUser);
        product = findViewById(R.id.btnProduct);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_product);

        proList = new ArrayList<>();
        proList.add(new Product(1, "Toy", 30000, "something"));

        ProductAdapter adapter = new ProductAdapter(proList);

        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, UserList.class);
                startActivity(intent);
            }
        });
    }
}