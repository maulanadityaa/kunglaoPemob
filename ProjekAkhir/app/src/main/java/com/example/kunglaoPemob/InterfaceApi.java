package com.example.kunglaoPemob;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceApi {
    @GET("book")
    Call<List<GetBookData>> getBook();

    @GET("pinjam/{id_user}")
    Call<List<GetPinjamData>> getPinjaman(@Path("id_user") String id_user);

    @POST("pinjam")
    Call<List<PostPinjam>> postPinjam(@Body PostPinjam pinjam);

    @DELETE("pinjam/{id_user}/{id_buku}")
    Call<Void> deletePost(
            @Path("id_user") String id_user,
            @Path("id_buku") int id_buku
    );
}
