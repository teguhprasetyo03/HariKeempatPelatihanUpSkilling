package com.googles.harikeempat.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.googles.harikeempat.dao.MahasiswaDao;
import com.googles.harikeempat.model.ModelMahasiswa;

@Database( entities = ModelMahasiswa.class , version = 1)
public abstract class AppDatabase extends RoomDatabase {

   public abstract MahasiswaDao mahasiswaDao();
}
