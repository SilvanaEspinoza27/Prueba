package com.example.prueba;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.clases.Article;
import com.example.prueba.clases.Author;
import com.example.prueba.clases.Galley;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleTextView.setText(article.getTitle());

        // Concatenate authors' names
        StringBuilder authors = new StringBuilder();
        for (Author author : article.getAuthors()) {
            if (authors.length() > 0) {
                authors.append(", ");
            }
            authors.append(author.getNombres());
        }
        holder.authorsTextView.setText(authors.toString());
        holder.htmlButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getDoi()));
            v.getContext().startActivity(browserIntent);
        });
        // Set button click listeners
        for (Galley galley : article.getGaleys()) {
            holder.pdfButton.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(galley.getUrlViewGalley().replace("view", "download")));
                v.getContext().startActivity(browserIntent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView pagesTextView;
        public TextView authorsTextView;
        public Button htmlButton;
        public Button pdfButton;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            authorsTextView = itemView.findViewById(R.id.article_authors);
            htmlButton = itemView.findViewById(R.id.button_html);
            pdfButton = itemView.findViewById(R.id.button_pdf);
        }
    }

}
