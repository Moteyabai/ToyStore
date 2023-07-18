package com.example.toystore.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.DBHelper;
import com.example.toystore.Models.Product;
import com.example.toystore.ProductList;
import com.example.toystore.ProductUpdate;
import com.example.toystore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productList;
    DBHelper helper;

    int proIDValue;

    private Context mContext;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.mContext = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Product product = productList.get(position);
        holder.proName.setText(String.valueOf(product.getProductName()));
        holder.desc.setText(product.getDescription());
        holder.proId.setText(String.valueOf(product.getProductID()));
        holder.price.setText((int) product.getPrice() + "$");
        String imageURL = product.getImage();
        Picasso.get().load(imageURL).into(holder.image);

        proIDValue = product.getProductID();

        //Edit

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mContext, holder.edit);
                popupMenu.inflate(R.menu.edit_menu);
                helper = new DBHelper(mContext);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.update_menu) {
                            Intent intent = new Intent(mContext, ProductUpdate.class);
                            intent.putExtra("id", String.valueOf(product.getProductID()));
                            intent.putExtra("proName", product.getProductName());
                            intent.putExtra("price", String.valueOf(product.getPrice()));
                            intent.putExtra("desc", product.getDescription());
                            intent.putExtra("image", product.getImage());
                            mContext.startActivity(intent);
                        } else if (id == R.id.delete_menu) {
                            if(helper.deleteProduct(product.getProductID())){
                                productList.remove(position);
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }else{
                                Toast.makeText(mContext, "Error!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        return false;
                    }
                });
                popupMenu.show();
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
        ImageButton edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proId = (TextView) itemView.findViewById(R.id.proId);
            proName = (TextView) itemView.findViewById(R.id.txtProName);
            desc = (TextView) itemView.findViewById(R.id.txtDesc);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.viewInage);
            edit = (ImageButton) itemView.findViewById(R.id.options);
        }
    }
}
