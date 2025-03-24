package com.example.prueba.servicios;

import com.example.prueba.clases.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {
    @GET("ws/pubs.php")
    Call<List<Article>> getArticles(@Query("i_id") String issueId);
}
