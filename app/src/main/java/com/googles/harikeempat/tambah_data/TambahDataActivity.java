package com.googles.harikeempat.tambah_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.googles.harikeempat.R;
import com.googles.harikeempat.database.AppDatabase;
import com.googles.harikeempat.lihat_data.LihatDataActivity;
import com.googles.harikeempat.model.ModelMahasiswa;

public class TambahDataActivity extends AppCompatActivity {
    TextView datamhs;
    EditText edtNama, edtNpm , edtAlamat;
    Button submit;
    AppDatabase db;
    ModelMahasiswa modelMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tambah_data );

        getSupportActionBar().setTitle( "Tambah Data Mahasiswa" );

        db = Room.databaseBuilder(
                getApplicationContext(), AppDatabase.class , "db_mahasiswa").build();

        modelMahasiswa = (ModelMahasiswa) getIntent().getSerializableExtra( "data" );

        datamhs = findViewById( R.id.tv_data_mhs );
        edtNama = findViewById( R.id.edt_nama );
        edtNpm = findViewById( R.id.edt_npm );
        edtAlamat = findViewById( R.id.edt_alamat );
        submit = findViewById( R.id.btn_submit );

        if (modelMahasiswa != null){
            edtNpm.setText(Integer.toString(modelMahasiswa.getNpm()));
            edtNama.setText( modelMahasiswa.getNama());
            edtAlamat.setText( modelMahasiswa.getAlamat());
            submit.setText( "UPDATE" );

            submit.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modelMahasiswa.setNpm( Integer.parseInt(edtNpm.getText().toString()));
                    modelMahasiswa.setNama(edtNama.getText().toString());
                    modelMahasiswa.setAlamat(edtAlamat.getText().toString());
                    updateMahasiswa( modelMahasiswa );
                    finish();
//                    Intent intent = new Intent( getApplicationContext() , LihatDataActivity.class);
//                    startActivity( intent );
                }
            });
        } else {
            submit.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int npm = Integer.parseInt( edtNpm.getText().toString() );
                    String nama = edtNama.getText().toString();
                    String alamat = edtAlamat.getText().toString();

                    ModelMahasiswa data = new ModelMahasiswa( npm, nama, alamat );
                    insertMahasiswa( data );
                    finish();
//                    Intent intent = new Intent( getApplicationContext() , LihatDataActivity.class);
//                    startActivity( intent );
                }
            } );
        }
    }

    @SuppressLint( "StaticFieldLeak" )
    public void insertMahasiswa(final ModelMahasiswa modelMahasiswa){
        new AsyncTask<Void , Void , Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status =  db.mahasiswaDao().insertMahasiswa( modelMahasiswa );
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute( aLong );
                Toast.makeText( TambahDataActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT ).show();
            }

        }.execute();
    }

    @SuppressLint( "StaticFieldLeak" )
    public void updateMahasiswa(final ModelMahasiswa modelMahasiswa){
        new AsyncTask<Void , Void , Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status =  db.mahasiswaDao().updateMahasiswa( modelMahasiswa );
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute( aLong );
                Toast.makeText( TambahDataActivity.this, "Data Mahasiswa Berhasil Di Update", Toast.LENGTH_SHORT ).show();
            }
        }.execute();
    }
}
