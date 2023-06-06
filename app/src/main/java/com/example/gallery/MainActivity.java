package com.example.gallery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.malw.gallery.R;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Main_image> images = new ArrayList<>();
    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if ((android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU && checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            File[] files = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/").listFiles(file -> file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg") || file.getName().toLowerCase().endsWith(".png"));
            if (files != null) for (File f : files) images.add(new Main_image(f.getName(), f.getAbsolutePath()));
            RecyclerView recyclerView = findViewById(R.id.gallery);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            recyclerView.setItemViewCacheSize(20);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
            recyclerView.setAdapter(new Main_Adapter(getApplicationContext(), images));
        } else {
            if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 1000);
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                finish();
                startActivity(getIntent());
            } else {
                Toast.makeText(this, "Нет разрешения на доступ к файлам!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Нет разрешения на доступ к файлам");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Открыть настройки",
                        (dialog, id) -> {
                            dialog.cancel();
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        });

                builder1.setNegativeButton(
                        "Закрыть",
                        (dialog, id) -> {
                            dialog.cancel();
                            finish();
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }
}