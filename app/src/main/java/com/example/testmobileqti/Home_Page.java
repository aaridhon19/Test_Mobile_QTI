package com.example.testmobileqti;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Home_Page extends AppCompatActivity {

    private TextView nameUser, emailUser;

    private RelativeLayout btnLogout, btnHome, btnAsset, btnInput;

//    private View avatarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        nameUser = findViewById(R.id.name_user);
        emailUser = findViewById(R.id.email_user);

        // Mendapatkan nilai email_user dari Intent
        String userEmail = getIntent().getStringExtra("email_user");

        // Mengatur nilai emailUser sesuai dengan nilai yang diterima dari Intent
        emailUser.setText(userEmail);

        CircleAvatarUser avatarUser = findViewById(R.id.avatar_user);

        btnHome = findViewById(R.id.button_home);

        btnAsset = findViewById(R.id.button_asset);
        btnInput = findViewById(R.id.button_trig);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputPage();
            }
        });

        btnLogout = findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    private void listPage(){

    }
    private void inputPage(){
        Intent intent = new Intent(Home_Page.this, Input_Asset_Page.class);
        startActivity(intent);
        finish(); // Selesaikan aktivitas saat ini (Home_Page)
    }

    private void logout() {
        // Lakukan tindakan logout, seperti membersihkan sesi atau data pengguna

        // Redirect ke menu login
        Intent intent = new Intent(Home_Page.this, Login_Page.class);
        startActivity(intent);
        finish(); // Selesaikan aktivitas saat ini (Home_Page)
    }


    private void showLogoutDialog() {
        // Buat dialog
        Dialog customDialog = new Dialog(this);

        // Set konten dialog dari layout XML
        customDialog.setContentView(R.layout.dialog_confirm);

        // Atur elemen UI atau lakukan tindakan lainnya
        TextView dialogTitle = customDialog.findViewById(R.id.title_dialog_confirm);
        dialogTitle.setText("Logout");

        // Atur elemen UI atau lakukan tindakan lainnya
        TextView dialogContent = customDialog.findViewById(R.id.content_dialog_confirm);
        dialogContent.setText("When you want to use this app,\n" +
                "you have to relogin, are you sure?");

        // Atur elemen UI atau lakukan tindakan lainnya
        TextView dialogLogout = customDialog.findViewById(R.id.btn_confirm);
        dialogLogout.setText("Logout");

        // Tambahkan aksi klik pada tombol Ya
        RelativeLayout confirmButton = customDialog.findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lakukan tindakan logout
                logout();
                customDialog.dismiss(); // Menutup dialog
            }
        });

        // Tambahkan aksi klik pada tombol Batal
        RelativeLayout cancelButton = customDialog.findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss(); // Menutup dialog
            }
        });

        // Tampilkan dialog
        customDialog.show();
    }
}