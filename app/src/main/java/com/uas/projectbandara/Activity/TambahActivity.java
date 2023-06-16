package com.uas.projectbandara.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.projectbandara.API.APIRequestData;
import com.uas.projectbandara.API.RetroServer;
import com.uas.projectbandara.Model.ModelResponse;
import com.uas.projectbandara.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etSejarah, etLuasBandara, etKota, etTahunBerdiri;
    private Button btnTambah;
    private String nama, sejarah, luasbandara, kota, tahunberdiri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etSejarah = findViewById(R.id.et_sejarah);
        etLuasBandara = findViewById(R.id.et_luas_bandara);
        etKota = findViewById(R.id.et_kota);
        etTahunBerdiri = findViewById(R.id.et_tahun_berdiri);
        btnTambah = findViewById(R.id.btn_Tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                sejarah = etSejarah.getText().toString();
                luasbandara = etLuasBandara.getText().toString();
                kota = etKota.getText().toString();
                tahunberdiri = etTahunBerdiri.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("nama tidak boleh kosong");
                } else if (sejarah.trim().isEmpty()) {
                    etSejarah.setError("sejarah tidak boleh kosong");
                } else if (luasbandara.trim().isEmpty()) {
                    etLuasBandara.setError("luas tidak boleh kosong");
                } else if (kota.trim().isEmpty()) {
                    etKota.setError("kota tidak boleh kosong");
                } else if (tahunberdiri.trim().isEmpty()) {
                    etTahunBerdiri.setError("tahun tidak boleh kosong");
                } else {
                    tambahBandara();
                }
            }
        });
    }

    private void tambahBandara() {
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, sejarah, luasbandara, kota, tahunberdiri);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Error:Gagal menghubungi server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini", "Errornya itu: " + t.getMessage());
            }
        });
    }
}



