package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toystore.Adapters.CartAdapter;
import com.example.toystore.Models.Items;
import com.example.toystore.Models.ShoppingCart;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ShoppingCart cart;
    Button backBtn,checkoutBtn;
    TextView total;
    ArrayList<Items> itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView rv = (RecyclerView) findViewById(R.id.cartView);
        backBtn = (Button) findViewById(R.id.backBtn);
        checkoutBtn = (Button) findViewById(R.id.checkoutBtn);
        total = (TextView) findViewById(R.id.total);

        cart = new ShoppingCart(CartActivity.this);

        total.setText("Total: "+(int) cart.getTotalPrice()+"$");

        itemsList = new ArrayList<>();
        itemsList = (ArrayList<Items>) cart.getItems();

        CartAdapter adapter = new CartAdapter(CartActivity.this, itemsList);

        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(CartActivity.this));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.clearCart();
                Toast.makeText(CartActivity.this, "Thank you for buying", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }
}