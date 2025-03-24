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

import com.example.prueba.clases.Article;
import com.example.prueba.servicios.ArticleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String issueId = getIntent().getStringExtra("issue_id");
        fetchArticles(issueId);
    }

    private void fetchArticles(String issueId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        Call<List<Article>> call = service.getArticles(issueId);

        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articleList = response.body();
                    adapter = new ArticleAdapter(articleList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("ArticleActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.e("ArticleActivity", "Failed to fetch articles", t);
            }
        });
    }
}