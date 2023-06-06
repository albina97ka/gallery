package com.example.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.malw.gallery.R;

public class Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new Screen_adapter(getSupportFragmentManager(), getIntent().getParcelableArrayListExtra("images")));
        viewPager.setCurrentItem(getIntent().getIntExtra("image_index", 0));
    }
    public void onClick(View view) {
        startActivity(new Intent(Screen.this, MainActivity.class));}
}
