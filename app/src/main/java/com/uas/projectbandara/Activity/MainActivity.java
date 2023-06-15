package com.uas.projectbandara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.projectbandara.Model.ModelBandara;
import com.uas.projectbandara.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBandara;
    private FloatingActionButton fabTambah;
    private ProgressBar pbBandara;
    private RecyclerView.Adapter adBandara;
    private RecyclerView.LayoutManager lmBandara;
    private List<ModelBandara> listbandara = new ArrayList<>();

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
    public void retrieveBandara(){
        pbBandara.setVisibility(View.VISIBLE);

    }
}