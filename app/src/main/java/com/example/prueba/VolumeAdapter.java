package com.example.prueba;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prueba.clases.Volume;

import java.util.List;

public class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.ViewHolder> {
    private List<Volume> volumeList;

    public VolumeAdapter(List<Volume> volumeList) {
        this.volumeList = volumeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volume, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Volume volume = volumeList.get(position);
        holder.titleTextView.setText(volume.getTitle());
        // Load image using a library like Glide or Picasso
        Glide.with(holder.coverImageView.getContext())
                .load(volume.getCover())
                .into(holder.coverImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ArticleActivity.class);
            intent.putExtra("issue_id", volume.getIssue_id());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return volumeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverImageView;
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.volume_cover);
            titleTextView = itemView.findViewById(R.id.volume_title);
        }
    }
}
