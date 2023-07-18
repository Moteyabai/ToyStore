package com.example.toystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.Adapters.HomeAdapter;
import com.example.toystore.Adapters.ProductAdapter;
import com.example.toystore.Models.Product;
import com.example.toystore.Models.ShoppingCart;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Button logout;
    ImageButton cartBtn;
    TextView cartItems;

    ShoppingCart cart;
    DBHelper helper;

    ArrayList<Product> proList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        helper = new DBHelper(Home.this);
        cart = new ShoppingCart(Home.this);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_product);
        cartBtn = findViewById(R.id.cartButton);
        logout = findViewById(R.id.logout);
        cartItems = findViewById(R.id.cartItems);

        int cartSize = cart.getCartSize();
        if(cartSize==0){
            cartItems.setText(String.valueOf(cartSize));
        }else{
            cartItems.setText(String.valueOf(cartSize));
        }


        proList = new ArrayList<>();

        proList = helper.getproduct(Home.this);


        HomeAdapter adapter = new HomeAdapter(this,proList);

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
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}