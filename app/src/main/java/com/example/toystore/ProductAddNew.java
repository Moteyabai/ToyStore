package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProductAddNew extends AppCompatActivity {

    EditText proName, price, desc, image;
    Button createBtn;

    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_new);
        proName = (EditText) findViewById(R.id.proNameValue);
        price = (EditText) findViewById(R.id.priceValue);
        desc = (EditText) findViewById(R.id.descValue);
        createBtn = (Button) findViewById(R.id.proCreate);
        image = (EditText) findViewById(R.id.imageValue);

        helper = new DBHelper(this);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = proName.getText().toString();
                double priceV = Double.parseDouble(price.getText().toString());
                String descV = desc.getText().toString();
                String imageV = image.getText().toString();

                helper.insertProduct(name, priceV, descV, imageV);
                Intent intent = new Intent(ProductAddNew.this, ProductList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}