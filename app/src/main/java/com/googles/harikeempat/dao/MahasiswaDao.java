package com.googles.harikeempat.dao;

import android.view.Display;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.googles.harikeempat.model.ModelMahasiswa;

import java.util.List;

@Dao
public interface MahasiswaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMahasiswa (ModelMahasiswa modelMahasiswa);

    @Query( "SELECT * FROM tb_mahasiswa" )
    List<ModelMahasiswa> getMahasiswa();

    @Update
    int updateMahasiswa(ModelMahasiswa modelMahasiswa);

    @Delete
    int deleteMahasiswa(ModelMahasiswa modelMahasiswa);

    @Query( "SELECT * FROM tb_mahasiswa WHERE idMahasiswa = :id LIMIT 1" )
    ModelMahasiswa detailMahaiswa(int id);
}
