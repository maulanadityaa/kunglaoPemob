package com.example.kunglaoPemob;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarPinjam extends AppCompatActivity {

    TextView tvPinjam;
    RecyclerView recyclerView;
    AdapterPinjam adapter;
    LinearLayoutManager linearLayoutManager;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam);

        tvPinjam = findViewById(R.id.tv_pinjam);
        recyclerView = findViewById(R.id.rv_pinjam);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a86402a29a53.ngrok.io/api/") //ubah sesuai URL API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<GetPinjamData>> call = api.getPinjaman(userid);

        call.enqueue(new Callback<List<GetPinjamData>>() {
            @Override
            public void onResponse(Call<List<GetPinjamData>> call, Response<List<GetPinjamData>> response) {
                if (response.isSuccessful()) {
                    List<GetPinjamData> pinjam = response.body();
                    adapter = new AdapterPinjam(DaftarPinjam.this, pinjam);
                    recyclerView.setAdapter(adapter);
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<GetPinjamData>> call, Throwable t) {
                tvPinjam.setText(t.getMessage());
            }
        });
    }
}
