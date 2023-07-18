package com.example.toystore.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.DBHelper;
import com.example.toystore.Models.Items;
import com.example.toystore.Models.ShoppingCart;
import com.example.toystore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<Items> itemsList;
    ShoppingCart cart;

    int getId;
    private Context mContext;

    public CartAdapter(Context context, ArrayList<Items> itemsList) {
        this.mContext = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final Items items = itemsList.get(position);
        cart = new ShoppingCart(mContext);
        holder.itemName.setText(String.valueOf(items.getItemName()));
        holder.itemPrice.setText((int) items.getPrice() + "$");
        holder.quantity.setText("Quantity: "+items.getQuantity());
        String imageURL = items.getItemImage();
        Picasso.get().load(imageURL).into(holder.itemImage);

        getId = items.getId();
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart = new ShoppingCart(mContext);
                if(cart!=null){
                    cart.removeItem(position);
                    itemsList.remove(position);
                    Toast.makeText(mContext, "Deleted!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, itemName, itemPrice, quantity,total;

        ImageView itemImage;
        ImageButton remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.itemId);
            itemName = (TextView) itemView.findViewById(R.id.txtItemName);
            itemPrice = (TextView) itemView.findViewById(R.id.txtItemPrice);
            quantity = (TextView) itemView.findViewById(R.id.itemQuantity);
            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            remove = (ImageButton) itemView.findViewById(R.id.deleteItem);
        }
    }
}
