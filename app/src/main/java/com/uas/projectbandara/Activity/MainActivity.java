package com.uas.projectbandara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.projectbandara.API.APIRequestData;
import com.uas.projectbandara.API.RetroServer;
import com.uas.projectbandara.Adapter.AdapterBandara;
import com.uas.projectbandara.Model.ModelBandara;
import com.uas.projectbandara.Model.ModelResponse;
import com.uas.projectbandara.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBandara;
    private FloatingActionButton fabTambah;
    private ProgressBar pbBandara;
    private RecyclerView.Adapter adBandara;
    private RecyclerView.LayoutManager lmBandara;
    private List<ModelBandara> listBandara = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBandara = findViewById(R.id.rv_bandara);
        fabTambah = findViewById(R.id.fab_tambah);
        pbBandara = findViewById(R.id.pb_bandara);

        lmBandara = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBandara.setLayoutManager(lmBandara);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveBandara();
    }

    public void retrieveBandara() {
        pbBandara.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listBandara = response.body().getData();

                adBandara = new AdapterBandara(MainActivity.this, listBandara);
                rvBandara.setAdapter(adBandara);
                adBandara.notifyDataSetChanged();

                pbBandara.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
