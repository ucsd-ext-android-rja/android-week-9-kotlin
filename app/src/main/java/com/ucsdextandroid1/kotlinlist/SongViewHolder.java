package com.ucsdextandroid1.kotlinlist;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ucsdextandroid1.kotlinlist.Models.SongItem;

/**
 * Created by rjaylward on 2019-06-08
 */
public class SongViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView = itemView.findViewById(R.id.vsi_image);
    private TextView titleView = itemView.findViewById(R.id.vsi_title);
    private TextView subtitleView;

    private SongItem currentSongItem;
    private OnItemClickListener<SongItem> clickListener;

    public static SongViewHolder inflate(@NonNull ViewGroup parent) {
        //TODO replace with extension method parent.inflate(...)
        return new SongViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_song_item, parent, false));
    }

    private SongViewHolder(@NonNull View itemView) {
        super(itemView);

        subtitleView = itemView.findViewById(R.id.vsi_subtitle);

        itemView.setOnClickListener(v -> {
            if(clickListener != null && currentSongItem != null)
                clickListener.onItemClicked(currentSongItem);
        });
    }

    void bind(SongItem songItem) {
        this.currentSongItem = songItem;

        Picasso.get().load(songItem.getArtworkUrl())
                .placeholder(new ColorDrawable(Utils.randomColor()))
                .into(imageView);

        titleView.setText(songItem.getTrackName());
        subtitleView.setText(String.format("%s • %s", songItem.getAlbumName(), songItem.getArtistName()));
    }

    void setClickListener(OnItemClickListener<SongItem> clickListener) {
        this.clickListener = clickListener;
    }

}
