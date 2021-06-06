package com.example.kunglaoPemob;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarBuku extends AppCompatActivity {

    TextView tvMain;
    RecyclerView recyclerView;
    AdapterBuku adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        tvMain = findViewById(R.id.tv_buku);
        recyclerView = findViewById(R.id.rv_buku);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a86402a29a53.ngrok.io/api/") //ubah sesuai URL API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<GetBookData>> call = api.getBook();

        call.enqueue(new Callback<List<GetBookData>>() {
            @Override
            public void onResponse(Call<List<GetBookData>> call, Response<List<GetBookData>> response) {
                if (response.isSuccessful()) {
                    List<GetBookData> books = response.body();
                    adapter = new AdapterBuku(DaftarBuku.this, books);
                    recyclerView.setAdapter(adapter);
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<GetBookData>> call, Throwable t) {
                tvMain.setText(t.getMessage());
            }
        });
    }
}
