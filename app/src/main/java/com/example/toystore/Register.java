package com.example.toystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText email, password, confirm, name, phone;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.registerButton);
        signin = findViewById(R.id.loginButton);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String confirmPass = confirm.getText().toString();
                String userName = name.getText().toString();
                String userPhone = phone.getText().toString();
                int userRole = 1; // Default role is 1

                if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword) ||
                        TextUtils.isEmpty(confirmPass) || TextUtils.isEmpty(userName) ||
                        TextUtils.isEmpty(userPhone)) {
                    Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    if (userPassword.equals(confirmPass)) {
                        Boolean checker = DB.checkEmail(userEmail);
                        if (!checker) {
                            Boolean insert = DB.insertData(userEmail, userPassword, userName, userPhone, userRole);
                            if (insert) {
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Register.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}