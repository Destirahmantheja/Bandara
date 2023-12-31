package com.uas.projectbandara.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.uas.projectbandara.API.APIRequestData;
import com.uas.projectbandara.API.RetroServer;
import com.uas.projectbandara.Activity.MainActivity;
import com.uas.projectbandara.Activity.UbahActivity;
import com.uas.projectbandara.Model.ModelBandara;
import com.uas.projectbandara.Model.ModelResponse;
import com.uas.projectbandara.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdapterBandara extends RecyclerView.Adapter<AdapterBandara.VHBandara> {
    private Context ctx;
    private List<ModelBandara> listBandara;

    public AdapterBandara(Context ctx, List<ModelBandara> listBandara){
        this.ctx = ctx;
        this.listBandara = listBandara;
    }

    @NonNull
    @Override
    public VHBandara onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_bandara, parent, false);
        return new VHBandara(varView);
    }
    @Override
    public void onBindViewHolder(@NonNull VHBandara holder, int position) {
        ModelBandara MB = listBandara.get(position);
        holder.tvId.setText(MB.getId());
        holder.tvNama.setText(MB.getNama());
        holder.tvSejarah.setText(MB.getSejarah());
        holder.tvLuasbandara.setText(MB.getLuasbandara());
        holder.tvKota.setText(MB.getKota());
        holder.tvTahunberdiri.setText(MB.getTahunberdiri());
    }

    @Override
    public int getItemCount() { return listBandara.size(); }
    public class VHBandara extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvSejarah, tvLuasbandara, tvKota, tvTahunberdiri;
        Button btnHapus, btnDetail, btnUbah;

        public VHBandara(@NonNull View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvSejarah = itemView.findViewById(R.id.tv_sejarah);
            tvLuasbandara= itemView.findViewById(R.id.tv_luas_bandara);
            tvKota = itemView.findViewById(R.id.tv_kota);
            tvTahunberdiri = itemView.findViewById(R.id.tv_tahun_berdiri);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnDetail = itemView.findViewById(R.id.btn_detail);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { deleteBandara(tvId.getText().toString());}
            });

            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId",tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xSejarah", tvSejarah.getText().toString());
                    pindah.putExtra("xLuasbandara", tvLuasbandara.getText().toString());
                    pindah.putExtra("xKota", tvKota.getText().toString());
                    pindah.putExtra("xTahunberdiri", tvTahunberdiri.getText().toString());
                    ctx.startActivity(pindah);
                }
            });
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId",tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xSejarah", tvSejarah.getText().toString());
                    pindah.putExtra("xLuasbandara", tvLuasbandara.getText().toString());
                    pindah.putExtra("xKota", tvKota.getText().toString());
                    pindah.putExtra("xTahunberdiri", tvTahunberdiri.getText().toString());
                    ctx.startActivity(pindah);
                }
            });
        }
        void deleteBandara(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);
            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(retrofit2.Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "kode: " + kode+ "pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveBandara();
                }

                @Override
                public void onFailure(retrofit2.Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Error! Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


