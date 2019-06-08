package com.ucsdextandroid1.kotlinlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rjaylward on 2019-06-08
 */
public class Models {

    public class SearchResults {

        @SerializedName("results")
        private List<SongItem> songs;

        public List<SongItem> getSongs() {
            return songs;
        }

    }

    public class SongItem {

        @SerializedName("trackId")
        private long trackId;
        @SerializedName("trackName")
        private String trackName;
        @SerializedName("artistName")
        private String artistName;
        @SerializedName("collectionName")
        private String albumName;
        @SerializedName("artworkUrl100")
        private String artwork;
        @SerializedName("previewUrl")
        private String previewUrl;
        @SerializedName("trackTimeMillis")
        private long trackTime;

        public long getTrackId() {
            return trackId;
        }

        public String getTrackName() {
            return trackName;
        }

        public String getArtistName() {
            return artistName;
        }

        public String getAlbumName() {
            return albumName;
        }

        public String getArtworkUrl() {
            return artwork;
        }

        public String getPreviewUrl() {
            return previewUrl;
        }

        public long getTrackTime() {
            return trackTime;
        }

    }
}
