package com.example.kunglaoPemob;

import android.content.Context;
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

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.AdapterHolder> {

    private Context context;
    private List<GetBookData> dataList;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userid = user.getUid();

    public AdapterBuku(Context context, List<GetBookData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterBuku.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_buku, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBuku.AdapterHolder holder, int position) {

        final GetBookData getBookData = dataList.get(position);
        String judulBuku = getBookData.getJudul();
        String penulisBuku = getBookData.getPenulis();
        String kategoriBuku = getBookData.getKategori();

        holder.judul.setText("Judul Buku: " + judulBuku);
        holder.penulis.setText("Penulis: " + penulisBuku);
        holder.kategori.setText("Kategori: " + kategoriBuku);

        holder.bookData = getBookData;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView judul, penulis, kategori;
        Button btPinjam;
        GetBookData bookData;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.tv_judul);
            penulis = itemView.findViewById(R.id.tv_penulis);
            kategori = itemView.findViewById(R.id.tv_kategori);
            btPinjam = itemView.findViewById(R.id.btn_pinjam);


            btPinjam.setOnClickListener(new View.OnClickListener() {
                InterfaceApi api;

                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://a86402a29a53.ngrok.io/api/") //ubah sesuai URL API
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    api = retrofit.create(InterfaceApi.class);

                    PostPinjam post = new PostPinjam(userid, bookData.getId());

                    Call<List<PostPinjam>> call = api.postPinjam(post);

                    call.enqueue(new Callback<List<PostPinjam>>() {
                        @Override
                        public void onResponse(Call<List<PostPinjam>> call, Response<List<PostPinjam>> response) {
                            Toast toast = Toast.makeText(context.getApplicationContext(),
                                    "Berhasil meminjam buku." +"\n" + "Status Code: "
                                            + response.code(), Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        @Override
                        public void onFailure(Call<List<PostPinjam>> call, Throwable t) {
                            Toast toast = Toast.makeText(context.getApplicationContext(),
                                    t.getMessage(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            });
        }
    }
}
