package com.uas.projectbandara.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelBandara> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelBandara> getData() {
        return data;
    }
}
