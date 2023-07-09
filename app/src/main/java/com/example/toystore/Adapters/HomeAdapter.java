package com.example.toystore.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.DBHelper;
import com.example.toystore.Models.Product;
import com.example.toystore.ProductUpdate;
import com.example.toystore.R;

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

        proIDValue = product.getProductID();

        //Edit


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proId,proName,desc, price;
        Button buy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proId = (TextView) itemView.findViewById(R.id.proId);
            proName = (TextView) itemView.findViewById(R.id.txtProName);
            desc = (TextView) itemView.findViewById(R.id.txtDesc);
            price = (TextView) itemView.findViewById(R.id.price);
            buy = (Button) itemView.findViewById(R.id.buy);
        }
    }
}
