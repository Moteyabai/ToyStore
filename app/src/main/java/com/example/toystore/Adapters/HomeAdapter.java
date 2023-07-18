package com.example.toystore.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.DBHelper;
import com.example.toystore.ProductDetail;
import com.example.toystore.Models.Product;
import com.example.toystore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    ArrayList<Product> productList;
    DBHelper helper;

    int proIDValue;

    private Context mContext;

    public HomeAdapter(Context context, ArrayList<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_home_list, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Product product = productList.get(position);
        holder.proName.setText(String.valueOf(product.getProductName()));
        holder.desc.setText(product.getDescription());
        holder.proId.setText(String.valueOf(product.getProductID()));
        holder.price.setText((int) product.getPrice() + "$");
        String imageURL = product.getImage();
        Picasso.get().load(imageURL).into(holder.image);

        proIDValue = product.getProductID();

        //Edit
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetail.class);
                intent.putExtra("productId", product.getProductID());
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("image", product.getImage());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proId,proName,desc, price;

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proId = (TextView) itemView.findViewById(R.id.proId);
            proName = (TextView) itemView.findViewById(R.id.txtProName);
            desc = (TextView) itemView.findViewById(R.id.txtDesc);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.viewInage);
        }
    }

}
