package com.googles.harikeempat.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_mahasiswa")
public class ModelMahasiswa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int idMahasiswa;
    @ColumnInfo(name = "npm")
    int npm;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "alamat")
    String alamat;

    public ModelMahasiswa(int npm, String nama, String alamat) {
        this.npm = npm;
        this.nama = nama;
        this.alamat = alamat;
    }

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}
