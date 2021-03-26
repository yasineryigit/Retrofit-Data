package com.ossovita.retrofitdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ossovita.retrofitdata.adapter.PhotoAdapter;
import com.ossovita.retrofitdata.api.PhotosApi;
import com.ossovita.retrofitdata.model.Photos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    PhotosApi photosApi;
    public List<Photos> photos = new ArrayList<>();
    public RecyclerView recyclerView;
    public PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        photosApi = retrofit.create(PhotosApi.class);
        getPhotosByAlbumId(15);//resimleri çekip listeyi dolduracak
        //getPhotoById(40);
    }


    private void getPhotosByAlbumId(int albumId) {

        Call<List<Photos>> call = photosApi.getPhotosByAlbumId(albumId);//gelen verileri Photos türünde obje listesine dönüştürecek.

        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if(!response.isSuccessful()){//gelen veride hata varsa hata kodunu yaz
                    Toast.makeText(MainActivity.this, "Error code: \" + response.code()", Toast.LENGTH_SHORT).show();
                    return;
                }
                //hata yoksa komple gelen body'i obje listemize at
                photos = response.body();//recyclerView listemizi güncelledik
                showDataOnRecycler(photos);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPhotoById(int photoId) {

        Call<List<Photos>> call = photosApi.getPhotosById(photoId);//gelen verileri Photos türünde obje listesine dönüştürecek.

        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if(!response.isSuccessful()){//gelen veride hata varsa hata kodunu yaz
                    Toast.makeText(MainActivity.this, "Error code: \" + response.code()", Toast.LENGTH_SHORT).show();
                    return;
                }
                //hata yoksa komple gelen body'i obje listemize at
                photos = response.body();//recyclerView listemizi güncelledik
                showDataOnRecycler(photos);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void showDataOnRecycler(List<Photos> photos) {
        adapter = new PhotoAdapter(photos);
        recyclerView.setAdapter(adapter);//listeyi alıp recyclerı güncelleyecek
        adapter.notifyDataSetChanged();//listeyi güncellediğimizi adapter'a söylüyoruz
    }


}
