package com.example.toystore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "ToysStore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User(userID INTEGER primary key,email TEXT, password TEXT, name TETX, phone TEXT, role INTEGER)");
        db.execSQL("create Table Product(productID INTEGER primary key,username TEXT, product_name TEXT, price DECIMAL, category TEXT, description TEXT)");
        db.execSQL("create Table Orders(orderID INTEGER primary key,userID INTEGER, order_date DATE, total_amout DECIMAL, FOREIGN KEY (userID) REFERENCES User(userID))");
        db.execSQL("create Table OrdersDetail(orderDetailID INTEGER primary key,orderID INTEGER, productID INTEGER, quantity INTEGER, price DECIMAL, FOREIGN KEY (orderID) REFERENCES Orders(orderID),\n" +
                "  FOREIGN KEY (productID) REFERENCES Product(productID))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
