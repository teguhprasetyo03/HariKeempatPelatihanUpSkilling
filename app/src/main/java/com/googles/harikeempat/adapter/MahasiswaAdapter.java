package com.googles.harikeempat.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.googles.harikeempat.R;
import com.googles.harikeempat.database.AppDatabase;
import com.googles.harikeempat.model.ModelMahasiswa;
import com.googles.harikeempat.tambah_data.TambahDataActivity;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    Context context;
    List<ModelMahasiswa> dataMahasiswa;
    AppDatabase db;

    public MahasiswaAdapter(Context context, List<ModelMahasiswa> dataMahasiswa) {
        this.context = context;
        this.dataMahasiswa = dataMahasiswa;

        db = Room.databaseBuilder(context, AppDatabase.class , "db_mahasiswa").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_mahasiswa, parent, false );
        return new MahasiswaViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder holder, final int position) {

        holder.tvNama.setText( dataMahasiswa.get( position ).getNama() );
        holder.tvAlamat.setText( dataMahasiswa.get( position ).getAlamat() );
        holder.tvNpm.setText( Integer.toString( dataMahasiswa.get( position ).getNpm() ) );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMahasiswa modelMahasiswa = db.mahasiswaDao().detailMahaiswa( dataMahasiswa.get( position ).getIdMahasiswa() );
                Intent intent = new Intent( context, TambahDataActivity.class );
                intent.putExtra( "data", modelMahasiswa );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity( intent );

            }
        });
        holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle( "Yakin Ingin Menghapus Data ? " );
                alertDialog.setMessage( "Pilih Ok untuk Menghapus Data" )
                        .setCancelable( false )
                        .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Proses Delete
                                deleteMahasiswa( position );
                            }
                        } );

                alertDialog.setNegativeButton( "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                    }
                });
                alertDialog.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMahasiswa.size();
    }

    public void deleteMahasiswa(int position){
        db.mahasiswaDao().deleteMahasiswa( dataMahasiswa.get( position ) );
        dataMahasiswa.remove( position );
        notifyItemChanged( position );
        notifyItemRangeChanged( position, dataMahasiswa.size());
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        TextView tvNpm;
        TextView tvAlamat;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super( itemView );

            tvNama = itemView.findViewById( R.id.txt_nama_mhs );
            tvNpm = itemView.findViewById( R.id.txt_npm_mhs );
            tvAlamat = itemView.findViewById( R.id.txt_alamat_mhs );
        }
    }


}
