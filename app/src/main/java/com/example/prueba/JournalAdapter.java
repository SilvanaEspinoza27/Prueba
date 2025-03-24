package com.example.prueba;

import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prueba.clases.Journal;

import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {

    private List<Journal> journalList;

    public JournalAdapter(List<Journal> journalList) {
        this.journalList = journalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Journal journal = journalList.get(position);
        holder.textView.setText(journal.getName());
        holder.textView2.setText(Html.fromHtml(journal.getDescription(), Html.FROM_HTML_MODE_LEGACY));
        // Load image using a library like Glide or Picasso
        Glide.with(holder.imageView.getContext())
                .load(journal.getPortada())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VolumeActivity.class);
            intent.putExtra("journal_id", journal.getJournal_id());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_text);
            textView2 = itemView.findViewById(R.id.item_text2);
        }
    }
}
