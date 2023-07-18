package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductUpdate extends AppCompatActivity {

    TextView eId;
    EditText eProName, ePrice, eDesc, eImage;
    String id, name, desc,price, image;
    Button updateBtn;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        eProName = (EditText) findViewById(R.id.proNameUpdate);
        ePrice = (EditText) findViewById(R.id.priceUpdate);
        eDesc = (EditText) findViewById(R.id.descUpdate);
        eId = (TextView) findViewById(R.id.getId);
        eImage = (EditText) findViewById(R.id.imageUpdate);
        updateBtn = (Button) findViewById(R.id.proUpdate);

        helper = new DBHelper(this);

        //data from Intent

        Intent intent = getIntent();
        if(intent!=null){
            id = intent.getStringExtra("id");
            name = intent.getStringExtra("proName");
            price = intent.getStringExtra("price");
            desc = intent.getStringExtra("desc");
            image = intent.getStringExtra("image");
        }

        //Show product data
        eId.setText(id);
        eProName.setText(name);
        ePrice.setText(price);
        eDesc.setText(desc);
        eImage.setText(image);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nID = Integer.parseInt(eId.getText().toString());
                String nName = eProName.getText().toString();
                String nPrice = ePrice.getText().toString();
                String nDesc = eDesc.getText().toString();
                String nImage = eImage.getText().toString();

                if(helper.updateProduct(nID,nName,nPrice,nDesc, nImage)){
                    Toast.makeText(ProductUpdate.this, "Updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductUpdate.this, ProductList.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ProductUpdate.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}