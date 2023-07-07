package com.example.toystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertData(String email, String password, String name, String phone, int role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("name", name);
        values.put("phone", phone);
        values.put("role", role);

        long result = db.insert("User", null, values);
        return result != -1;
    }


    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE email = ?", new String[]{email});

        boolean emailExists = cursor.getCount() > 0;
        cursor.close();
        return emailExists;
    }

    public int getUserRole(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"role"};
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query("User", columns, selection, selectionArgs, null, null, null);

        int role = -1;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("role");
            if (columnIndex != -1) {
                role = cursor.getInt(columnIndex);
            }
        }
        cursor.close();
        return role;
    }
}
