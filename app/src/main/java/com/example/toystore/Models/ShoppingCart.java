package com.example.toystore.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Items> itemsList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ShoppingCart(Context context) {
        itemsList = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences("ShoppingCart", Context.MODE_PRIVATE);
        gson = new Gson();
        loadItemsFromSharedPreferences();
    }

    public void addItem(Items items) {
        Items newItem = getItemsByID(items.getId());
        if(newItem!=null){
            for (int i = 0; i < itemsList.size(); i++) {
                Items currentItem = itemsList.get(i);
                if (currentItem.getId() == items.getId()) {
                    items.setQuantity(items.getQuantity() + currentItem.getQuantity());
                    itemsList.set(i, items);
                    saveItemsToSharedPreferences();
                    return;
                }
            }
        }else{
            itemsList.add(items);
            saveItemsToSharedPreferences();
        }

    }

    public void removeItem(int id) {
        itemsList.remove(id);
        saveItemsToSharedPreferences();
    }

    public void clearCart(){
        itemsList.clear();
        saveItemsToSharedPreferences();
    }

    public List<Items> getItems() {
        return itemsList;
    }

    public int getCartSize(){
        return itemsList.size();
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Items items : itemsList) {
            totalPrice += items.getPrice() * items.getQuantity();
        }
        return totalPrice;
    }

    private Items getItemsByID(int id){
        Items newItem = null;
        for (Items items : itemsList) {
            if(items.getId() == id){
                newItem = items;
            }
        }
        return newItem;
    }

    private void saveItemsToSharedPreferences() {
        String json = gson.toJson(itemsList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cartItems", json);
        editor.apply();
    }

    private void loadItemsFromSharedPreferences() {
        String json = sharedPreferences.getString("cartItems", null);
        if (json != null) {
            Type type = new TypeToken<List<Items>>(){}.getType();
            itemsList = gson.fromJson(json, type);
        }
    }
}
