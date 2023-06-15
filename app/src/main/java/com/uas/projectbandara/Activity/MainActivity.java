package com.uas.projectbandara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.projectbandara.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBandara;
    private FloatingActionButton fabTambah;
    private ProgressBar pbBandara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}