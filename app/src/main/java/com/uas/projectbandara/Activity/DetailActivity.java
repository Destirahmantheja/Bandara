package com.uas.projectbandara.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.projectbandara.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvSejarah, tvLuasbandara, tvKota, tvTahunberdiri;
    private String yNama, ySejarah, yLuasbandara, yKota, yTahunberdiri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvSejarah = findViewById(R.id.tv_sejarah);
        tvLuasbandara = findViewById(R.id.tv_luas_bandara);
        tvKota = findViewById(R.id.tv_kota);
        tvTahunberdiri = findViewById(R.id.tv_tahun_berdiri);

        Intent terima = getIntent();
        yNama = terima.getStringExtra("xNama");
        ySejarah = terima.getStringExtra("xSejarah");
        yLuasbandara = terima.getStringExtra("xLuasbandara");
        yKota = terima.getStringExtra("xKota");
        yTahunberdiri = terima.getStringExtra("xTahunberdiri");

        tvNama.setText(yNama);
        tvSejarah.setText(ySejarah);
        tvLuasbandara.setText(yLuasbandara);
        tvKota.setText(yKota);
        tvTahunberdiri.setText(yTahunberdiri);

    }
}
