package com.example.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malw.gallery.R;

import java.io.File;
import java.util.ArrayList;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.ViewHolder> {
    private final ArrayList<Main_image> images;
    private final Context context;

    public Main_Adapter(Context context, ArrayList<Main_image> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(images.get(position).getPath(), viewHolder.img);
        viewHolder.img.setOnClickListener(v -> {
            context.startActivity(new Intent(context, Screen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("image_index", position).putParcelableArrayListExtra("images", images));
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.image);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    private void setImageFromPath(String path, ImageView image) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = Main_helper.decodeSampleBitmapFromPath(imgFile.getAbsolutePath(), 200, 200);
            image.setImageBitmap(myBitmap);
        }
    }

}