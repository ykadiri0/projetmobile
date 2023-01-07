package com.project.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.retrofit.List.MainActivityList;
import com.project.retrofit.R;
import com.project.retrofit.map.MapsActivity;

public class HomeActivity extends AppCompatActivity {
    com.google.android.material.button.MaterialButton fab,fab2,fab3,fab0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fab=findViewById(R.id.fab);
        fab2=findViewById(R.id.fab1);
        fab3=findViewById(R.id.fab2);
        fab0=findViewById(R.id.fab0);
        fab0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, MainActivityList.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, MainActivityPG.class));
            }
        });
        fab2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, MapsActivity.class));
            }
        });
        fab3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, FilterActivity.class));
            }
        });
    }
}