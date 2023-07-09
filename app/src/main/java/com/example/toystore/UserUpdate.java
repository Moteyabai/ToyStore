package com.example.toystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserUpdate extends AppCompatActivity {

    private EditText editEmail, editPassword, editName, editPhone, editRole;
    private Button btnUpdate;

    private String userID, email, password, name, phone;
    private int role;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        db = new DBHelper(this);

        // Ánh xạ các view
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editRole = findViewById(R.id.edit_role);
        btnUpdate = findViewById(R.id.btn_update);

        // Lấy thông tin người dùng từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            userID = intent.getStringExtra("userID");
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("password");
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            role = intent.getIntExtra("role", -1);

            // Hiển thị thông tin người dùng trên giao diện
            editEmail.setText(email);
            editPassword.setText(password);
            editName.setText(name);
            editPhone.setText(phone);
            editRole.setText(String.valueOf(role));
        }

        // Xử lý sự kiện nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin cập nhật từ giao diện
                String updatedEmail = editEmail.getText().toString();
                String updatedPassword = editPassword.getText().toString();
                String updatedName = editName.getText().toString();
                String updatedPhone = editPhone.getText().toString();
                int updatedRole = Integer.parseInt(editRole.getText().toString());

                // Cập nhật thông tin người dùng trong CSDL
                boolean updateSuccess = db.updateUser(userID, updatedEmail, updatedPassword, updatedName, updatedPhone, updatedRole);
                if (updateSuccess) {
                    Toast.makeText(UserUpdate.this, "User updated successfully", Toast.LENGTH_SHORT).show();
                    // Trở lại trang UserList
                    Intent intent = new Intent(UserUpdate.this, UserList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UserUpdate.this, "Failed to update user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
