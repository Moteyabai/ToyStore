package com.example.toystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ToysStore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (userID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, name TEXT, phone TEXT, role INTEGER)");
        db.execSQL("CREATE TABLE Product (productID INTEGER PRIMARY KEY AUTOINCREMENT, product_name TEXT, price DECIMAL, description TEXT, image BLOB)");
        db.execSQL("CREATE TABLE Orders (orderID INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER, order_date DATE, total_amount DECIMAL, FOREIGN KEY (userID) REFERENCES User(userID))");
        db.execSQL("CREATE TABLE OrdersDetail (orderDetailID INTEGER PRIMARY KEY AUTOINCREMENT, orderID INTEGER, productID INTEGER, quantity INTEGER, price DECIMAL, FOREIGN KEY (orderID) REFERENCES Orders(orderID), FOREIGN KEY (productID) REFERENCES Product(productID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS Orders");
        db.execSQL("DROP TABLE IF EXISTS OrdersDetail");
        onCreate(db);
    }

    public boolean insertUser(String email, String password, String name, String phone, int role) {
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

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM User", null);
    }

    public boolean deleteUser(String userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("User", "userID = ?", new String[]{userID});
        return rowsAffected > 0;
    }

    public boolean updateUser(String userID, String email, String password, String name, String phone, int role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("name", name);
        values.put("phone", phone);
        values.put("role", role);

        int rowsAffected = db.update("User", values, "userID = ?", new String[]{userID});
        return rowsAffected > 0;
    }
}
