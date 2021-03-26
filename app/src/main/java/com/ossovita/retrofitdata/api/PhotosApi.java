package com.ossovita.retrofitdata.api;

import com.ossovita.retrofitdata.model.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PhotosApi {

    @GET("photos")
    Call<List<Photos>> getPhotos();

    @GET("photos")
    Call<List<Photos>> getPhotosByAlbumId(@Query("albumId") int albumId);///photos?albumId=12 şeklinde query yapabilmek için

    @GET("photos")
    Call<List<Photos>> getPhotosById(@Query("id") int id);

}
