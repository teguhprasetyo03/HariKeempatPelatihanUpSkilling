package com.googles.harikeempat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.googles.harikeempat.database.AppDatabase;
import com.googles.harikeempat.lihat_data.LihatDataActivity;
import com.googles.harikeempat.tambah_data.TambahDataActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Button tambah = findViewById( R.id.btn_tambah );
        Button lihat  = findViewById( R.id.btn_lihat_data );

        // Berpindah ke Activity tambah data
        tambah.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this , TambahDataActivity.class ) );
            }
        } );

        //  Berpindah ke Activity Lihat Data
        lihat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this , LihatDataActivity.class ) );
            }
        } );


    }
}
