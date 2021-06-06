package com.example.kunglaoPemob;

import android.app.Activity;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterPinjam extends RecyclerView.Adapter<AdapterPinjam.AdapterHolder> {

    private Context context;
    private List<GetPinjamData> dataList;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();


    public AdapterPinjam(Context context, List<GetPinjamData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterPinjam.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_pinjam, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPinjam.AdapterHolder holder, int position) {

        final GetPinjamData getPinjamData = dataList.get(position);
        String judulBuku = getPinjamData.getJudul();
        String penulisBuku = getPinjamData.getPenulis();
        String kategoriBuku = getPinjamData.getKategori();

        holder.judul.setText("Judul Buku: " + judulBuku);
        holder.penulis.setText("Penulis: " + penulisBuku);
        holder.kategori.setText("Kategori: " + kategoriBuku);

        holder.pinjamData = getPinjamData;
    }

    @Override
    public int getItemCount() {
        return dataList.size() ;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView judul,penulis,kategori;
        Button btKembalikan;

        GetPinjamData pinjamData;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tv_judulPinjam);
            penulis = itemView.findViewById(R.id.tv_penulisPinjam);
            kategori = itemView.findViewById(R.id.tv_kategoriPinjam);
            btKembalikan = itemView.findViewById(R.id.btn_kembalikan);

            btKembalikan.setOnClickListener(new View.OnClickListener() {
                InterfaceApi api;

                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://a86402a29a53.ngrok.io/api/") //ubah sesuai URL API
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    api = retrofit.create(InterfaceApi.class);

                    Call<Void> call = api.deletePost(userid,pinjamData.getId());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast toast = Toast.makeText(context.getApplicationContext(),
                                    "Berhasil mengembalikan buku." +"\n" + "Status Code: "
                                            + response.code(), Toast.LENGTH_SHORT);
                            toast.show();

                            ((Activity) context).finish();
                            Intent intent = ((Activity) context).getIntent();
                            context.startActivity(intent);
                          }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast toast = Toast.makeText(context.getApplicationContext(),
                                    "Status: " + t.getMessage(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            });
        }
    }
}
