package com.example.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.chrisbanes.photoview.PhotoView;
import com.malw.gallery.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ScreenFr extends Fragment {
    private static final String ARG_IMAGE_PATH = "image_path";
    private Handler handler = new Handler();

    public static ScreenFr newInstance(String imagePath) {
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_PATH, imagePath);
        ScreenFr fragment = new ScreenFr();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo, container, false);
        String imagePath = getArguments() != null ? getArguments().getString(ARG_IMAGE_PATH) : null;

        if (imagePath != null) {
            PhotoView imageView = view.findViewById(R.id.image_view);
            imageView.setOnClickListener(v -> {
            });
            Picasso.get().load(new File(imagePath)).fit().centerInside().into(imageView);
        }
        return view;
    }

    private Main_image getImage() {
        if (getArguments() != null) {
            String imagePath = getArguments().getString(ARG_IMAGE_PATH);
            if (imagePath != null) {
                return new Main_image("", imagePath);
            }
        }
        return null;
    }
}
