package com.ossovita.retrofitdata.adapter;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ossovita.retrofitdata.MainActivity;
import com.ossovita.retrofitdata.R;
import com.ossovita.retrofitdata.model.Photos;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private static final String TAG = "PhotoAdapter";
    private List<Photos> photos;

    public PhotoAdapter(List<Photos> photos) {
        this.photos = photos;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView id,albumId,title;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            id=itemView.findViewById(R.id.tv_id);
            albumId = itemView.findViewById(R.id.tv_album_id);
            title = itemView.findViewById(R.id.tv_title);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: came");
        GlideUrl url = new GlideUrl(photos.get(position).getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Log.d(TAG, "onResponse: url: " + url);
        //holder.imageView.getContext dememizin sebebi paslayacak context almak
        Glide.with(holder.imageView.getContext()).load(url).into(holder.imageView);

        holder.title.setText(String.valueOf(photos.get(position).getTitle()));
        holder.id.setText(String.valueOf(photos.get(position).getId()));
        holder.albumId.setText(String.valueOf(photos.get(position).getAlbumId()));



    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
