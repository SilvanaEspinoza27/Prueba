package com.example.prueba;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.clases.Volume;
import com.example.prueba.servicios.VolumeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VolumeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolumeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String journalId = getIntent().getStringExtra("journal_id");
        fetchVolumes(journalId);
    }

    private void fetchVolumes(String journalId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VolumeService service = retrofit.create(VolumeService.class);
        Call<List<Volume>> call = service.getVolumes(journalId);

        call.enqueue(new Callback<List<Volume>>() {
            @Override
            public void onResponse(Call<List<Volume>> call, Response<List<Volume>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Volume> volumeList = response.body();
                    adapter = new VolumeAdapter(volumeList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("VolumeActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Volume>> call, Throwable t) {
                Log.e("VolumeActivity", "Failed to fetch volumes", t);
            }
        });
    }
}