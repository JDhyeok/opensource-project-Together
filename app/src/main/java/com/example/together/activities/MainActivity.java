package com.example.together.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.together.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton netflix;
    private ImageButton watcha;
    private ImageButton disney;
    private ImageButton tving;
    private ImageButton wavve;
    private ImageButton hbomax;
    private ImageButton more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        netflix=findViewById(R.id.netflix);
        netflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, PostListActivity.class);
                startActivity(intent);
            }
        });

        watcha=findViewById(R.id.watcha);
        watcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WatchabbsActivity.class);
                startActivity(intent);
            }
        });

        disney=findViewById(R.id.disney);
        disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DisneybbsActivity.class);
                startActivity(intent);
            }
        });

        tving=findViewById(R.id.tving);
        tving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TvingbbsActivity.class);
                startActivity(intent);
            }
        });

        wavve=findViewById(R.id.wavve);
        wavve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, WavvebbsActivity.class);
                startActivity(intent);
            }
        });

        hbomax=findViewById(R.id.hbomax);
        hbomax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, HbomaxbbsActivity.class);
                startActivity(intent);
            }
        });

        more=findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MoreActivity.class);
                startActivity(intent);
            }
        });
    }
}