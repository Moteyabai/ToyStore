package com.example.toystore.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toystore.R;

public class OrderDetail extends AppCompatActivity {
    private TextView idTextView, nameTextView, priceTextView, descriptionTextView, quantityTextView;


    private Button decreaseButton, increaseButton, addToCartButton;


    private Product product;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Ánh xạ các view
        idTextView = findViewById(R.id.id);
        nameTextView = findViewById(R.id.name);
        priceTextView = findViewById(R.id.price);
        descriptionTextView = findViewById(R.id.description);
        quantityTextView = findViewById(R.id.quantity);
        decreaseButton = findViewById(R.id.decreaseButton);
        increaseButton = findViewById(R.id.increaseButton);
        addToCartButton = findViewById(R.id.addToCartButton);

        // Lấy thông tin sản phẩm từ Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("productId") && intent.hasExtra("productName")
                && intent.hasExtra("price") && intent.hasExtra("description")) {
            int productId = intent.getIntExtra("productId", 0);
            String productName = intent.getStringExtra("productName");
            double price = intent.getDoubleExtra("price", 0);
            String description = intent.getStringExtra("description");

            // Tạo đối tượng Product từ thông tin nhận được
            product = new Product(productId, productName, price, description);

            // Hiển thị thông tin sản phẩm
            idTextView.setText(String.valueOf(product.getProductID()));
            nameTextView.setText(product.getProductName());
            priceTextView.setText(String.valueOf(product.getPrice() + "$"));
            descriptionTextView.setText(product.getDescription());
        }

        // Thiết lập sự kiện cho các nút tăng/giảm số lượng
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity--;
                    quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        // Thiết lập sự kiện cho nút "Add to Cart"
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thêm sản phẩm vào giỏ hàng với số lượng hiện tại
                // TODO: Thêm mã logic xử lý thêm sản phẩm vào giỏ hàng ở đây
            }
        });
    }
}
