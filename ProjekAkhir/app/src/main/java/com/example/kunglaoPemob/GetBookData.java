package com.example.kunglaoPemob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBookData {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("penulis")
    @Expose
    private String penulis;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("stock")
    @Expose
    private int stock;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
