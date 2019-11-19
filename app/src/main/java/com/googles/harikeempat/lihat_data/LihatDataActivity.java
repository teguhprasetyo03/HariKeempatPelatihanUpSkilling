package com.googles.harikeempat.lihat_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.googles.harikeempat.R;
import com.googles.harikeempat.adapter.MahasiswaAdapter;
import com.googles.harikeempat.database.AppDatabase;
import com.googles.harikeempat.model.ModelMahasiswa;

import java.util.ArrayList;

public class LihatDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MahasiswaAdapter adapter;
    ArrayList<ModelMahasiswa> data;
    AppDatabase db;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lihat_data );

        getSupportActionBar().setTitle( "Lihat Data Mahasiswa" );

        recyclerView = findViewById( R.id.recycler_mahasiswa );

        swipe = findViewById( R.id.swipe_refresh );

        swipe.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        } );

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class , "db_mahasiswa").allowMainThreadQueries().build();
        getData();
    }

    void getData(){
        data = new ArrayList<>();
        data.clear();
        data.addAll(db.mahasiswaDao().getMahasiswa());
        adapter = new MahasiswaAdapter(getApplicationContext(), data  );

        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext()));
        recyclerView.setAdapter( adapter );
        swipe.setRefreshing( false );
    }

}
