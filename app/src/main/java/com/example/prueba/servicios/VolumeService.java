package com.example.prueba.servicios;

import com.example.prueba.clases.Volume;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VolumeService {
    @GET("ws/issues.php")
    Call<List<Volume>> getVolumes(@Query("j_id") String journalId);
}
