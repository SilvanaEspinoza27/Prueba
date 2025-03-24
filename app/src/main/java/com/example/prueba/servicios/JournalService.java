package com.example.prueba.servicios;

import com.example.prueba.clases.Journal;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JournalService {
    @GET("ws/journals.php")
    public Call<List<Journal>> getJournals();
}
