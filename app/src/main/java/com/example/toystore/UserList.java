package com.example.toystore;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.Adapters.UserAdapter;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, email, password, name, phone;
    ArrayList<Integer> role;
    DBHelper db;
    UserAdapter adapter;
    private static final int REQUEST_CODE_USER_UPDATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        db = new DBHelper(this);
        id = new ArrayList<>();
        email = new ArrayList<>();
        password = new ArrayList<>();
        name = new ArrayList<>();
        phone = new ArrayList<>();
        role = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        adapter = new UserAdapter(this, id, email, password, name, phone, role);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

    }

    private void displaydata() {
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(UserList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                email.add(cursor.getString(1));
                password.add(cursor.getString(2));
                name.add(cursor.getString(3));
                phone.add(cursor.getString(4));
                role.add(cursor.getInt(5));
            }
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_USER_UPDATE && resultCode == Activity.RESULT_OK) {
            // Nhận kết quả từ UserUpdate và thực hiện cập nhật danh sách người dùng
            displaydata();
        }
    }


    public void onUserClick(int position) {
        // Chuyển sang trang UserUpdate và truyền thông tin người dùng cần chỉnh sửa
        Intent intent = new Intent(UserList.this, UserUpdate.class);
        intent.putExtra("userID", id.get(position));
        intent.putExtra("email", email.get(position));
        intent.putExtra("password", password.get(position));
        intent.putExtra("name", name.get(position));
        intent.putExtra("phone", phone.get(position));
        intent.putExtra("role", role.get(position));
        startActivityForResult(intent, REQUEST_CODE_USER_UPDATE); // Sử dụng startActivityForResult() để nhận kết quả từ UserUpdate
    }


}