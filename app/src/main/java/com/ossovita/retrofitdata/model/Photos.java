package com.ossovita.retrofitdata.model;

import com.google.gson.annotations.SerializedName;

public class Photos {
    int id;
    int albumId;
    String title;
    String url;

    public Photos(int id, int albumId, String title, String url) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
