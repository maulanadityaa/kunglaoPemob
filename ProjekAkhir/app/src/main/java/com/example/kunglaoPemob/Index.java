package com.example.kunglaoPemob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Index extends AppCompatActivity implements View.OnClickListener {

    Button btnBooks, btnPinjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnBooks = findViewById(R.id.btn_books);
        btnPinjaman = findViewById(R.id.btn_peminjaman);

        btnPinjaman.setOnClickListener(this);
        btnBooks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnBooks.getId()) {
            Intent intent = new Intent(getApplicationContext(), DaftarBuku.class);
            startActivity(intent);
        }
        if (v.getId() == btnPinjaman.getId()) {
            Intent intent = new Intent(getApplicationContext(), DaftarPinjam.class);
            startActivity(intent);
        }
    }
}
