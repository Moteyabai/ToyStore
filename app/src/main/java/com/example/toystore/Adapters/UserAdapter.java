package com.example.toystore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toystore.DBHelper;
import com.example.toystore.R;
import com.example.toystore.UserUpdate;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private DBHelper db;
    private ArrayList<String> id, email, password, name, phone;
    private ArrayList<Integer> role;

    public UserAdapter(Context context, ArrayList<String> id, ArrayList<String> email, ArrayList<String> password, ArrayList<String> name, ArrayList<String> phone, ArrayList<Integer> role) {
        this.context = context;
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userID.setText(String.valueOf(id.get(position)));
        holder.email.setText(String.valueOf(email.get(position)));
        holder.password.setText(String.valueOf(password.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));
        holder.role.setText(String.valueOf(role.get(position)));

        final int itemPosition = position; // Tạo một biến cuối mới và gán giá trị của position

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserData(id.get(itemPosition)); // Sử dụng biến cuối trong phương thức onClick
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClick(itemPosition); // Gọi phương thức onUserClick khi bấm vào nút Update
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userID, email, password, name, phone, role;
        Button update, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            role = itemView.findViewById(R.id.role);

            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteUserData(String userID) {
        boolean deleteSuccess = db.deleteUser(userID);

        if (deleteSuccess) {
            Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();

            int index = id.indexOf(userID);
            if (index != -1) {
                id.remove(index);
                email.remove(index);
                password.remove(index);
                name.remove(index);
                phone.remove(index);
                role.remove(index);
                notifyDataSetChanged();
            }
        } else {
            Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
        }
    }

    public void onUserClick(int position) {
        // Chuyển sang trang UserUpdate và truyền thông tin người dùng cần chỉnh sửa
        Intent intent = new Intent(context, UserUpdate.class);
        intent.putExtra("userID", id.get(position));
        intent.putExtra("email", email.get(position));
        intent.putExtra("password", password.get(position));
        intent.putExtra("name", name.get(position));
        intent.putExtra("phone", phone.get(position));
        intent.putExtra("role", role.get(position));
        context.startActivity(intent);
    }
}